package ru.itmo.reactivejava.service;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.DrugMapper;
import ru.itmo.reactivejava.mapper.PharmacyDrugMapper;
import ru.itmo.reactivejava.mapper.PharmacyMapper;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.DrugRequest;
import ru.itmo.reactivejava.payload.request.PharmacyDrugRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.repository.DrugRepository;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;

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

    public Mono<Float> findPrice(long pharmacyId, long drugId) {
        return drugRepository.findById(drugId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Лекарство не найдено")))
                .map(Drug::getPrice);
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
