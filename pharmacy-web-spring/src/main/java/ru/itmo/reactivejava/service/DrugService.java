package ru.itmo.reactivejava.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.DrugMapper;
import ru.itmo.reactivejava.mapper.PharmacyDrugMapper;
import ru.itmo.reactivejava.payload.request.DrugRequest;
import ru.itmo.reactivejava.payload.request.PharmacyDrugRequest;
import ru.itmo.reactivejava.payload.response.DrugResponse;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.payload.response.PharmacyDrugResponse;
import ru.itmo.reactivejava.repository.DrugRepository;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    private final PharmacyDrugRepository pharmacyDrugRepository;

    public DrugService(DrugRepository drugRepository, PharmacyDrugRepository pharmacyDrugRepository) {
        this.drugRepository = drugRepository;
        this.pharmacyDrugRepository = pharmacyDrugRepository;
    }


    public Mono<MessageResponse> addDrug(DrugRequest drugRequest) {

        return drugRepository.save(DrugMapper.mapToDrug(drugRequest))
                .map(savedDrug -> new MessageResponse("Лекарство успешно добавлено"));
    }

    public Mono<MessageResponse> addDrugToPharmacy(PharmacyDrugRequest pharmacyDrugRequest) {
        return pharmacyDrugRepository.save(PharmacyDrugMapper.mapPharmacyDrug(pharmacyDrugRequest))
                .map(savedDrug -> new MessageResponse("Поставка успешно добавлена"));
    }

    public Mono<List<PharmacyDrugResponse>> getDrugsFromPharmacy(long id) {
        return pharmacyDrugRepository.findByPharmacyId(id)
                .flatMap(pharmacyDrug ->
                        drugRepository.findById(pharmacyDrug.getDrugId())
                                .map(drug -> {
                                    DrugResponse drugResponse = DrugMapper.mapToDrugResponse(drug);
                                    return PharmacyDrugMapper.mapToPharmacyDrugResponse(pharmacyDrug, drugResponse);
                                })
                )
                .collectList();
    }


    public Mono<Float> findPrice(long pharmacyId, long drugId) {
        return drugRepository.findById(drugId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Лекарство не найдено")))

                .flatMap(drug -> pharmacyDrugRepository.findByPharmacyIdAndDrugId(pharmacyId, drugId)
                        .switchIfEmpty(Mono.error(new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "В данной аптеке лекарство не найдено")))

                        .map(pharmacyDrug -> {
                            double basePrice = drug.getPrice();

                            double S  = pharmacyDrug.getQuantity();
                            double S0 = pharmacyDrug.getInitial_quantity();

                            LocalDate now = LocalDate.now();
                            long d = ChronoUnit.DAYS.between(now, drug.getExpirationDate());
                            if (d < 0) d = 0;

                            long dMax = ChronoUnit.DAYS.between(drug.getManufactureDate(), drug.getExpirationDate());
                            if (dMax < 1) dMax = 1;

                            double scale = 0.0;
                            if (dMax > 1) {
                                scale = (double)(d - 1) / (double)(dMax - 1);
                                if (scale < 0) scale = 0;
                                if (scale > 1) scale = 1;
                            }

                            float ALPHA = 18.5F;
                            float BETA = 0.2F;

                            double deficitFactor = 1 + ALPHA * (1 - Math.pow(S / S0, BETA)) * scale;

                            double shelfFactor = 0.8 + 0.2 * scale;

                            double F = deficitFactor * shelfFactor;

                            double finalPrice = basePrice * F;

                            return (float) finalPrice;
                        })
                );
    }


    public Mono<Void> reduceQuantity(Long pharmacyId, Long drugId, int quantity) {
        return pharmacyDrugRepository.findByPharmacyIdAndDrugId(pharmacyId, drugId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Лекарство не найдено")))
                .flatMap(entity -> {
                    if (entity.getQuantity() < quantity) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недостаточно лекарств в наличии"));
                    }
                    int newQuantity = entity.getQuantity() - quantity;
                    return pharmacyDrugRepository.updateQuantity(pharmacyId, drugId, newQuantity);
                })
                .flatMap(rowsUpdated -> {
                    if (rowsUpdated == 0) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Обновление не удалось"));
                    }
                    return Mono.empty();
                });
    }
}
