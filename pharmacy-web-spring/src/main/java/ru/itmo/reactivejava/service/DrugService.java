package ru.itmo.reactivejava.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.DrugMapper;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.DrugRequest;
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


    public Mono<Float> findPrice(long pharmacyId, long drugId) {
        return pharmacyDrugRepository.findByPharmacyIdAndDrugId(pharmacyId, drugId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Лекарство не найдено")))
                .map(PharmacyDrug::getPrice);
    }

    public Mono<Void> reduceQuantity(Long pharmacyId, Long drugId, int quantity) {
        return pharmacyDrugRepository.findByPharmacyIdAndDrugId(pharmacyId, drugId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Лекарство не найдено")))
                .flatMap(entity -> {
                    if (entity.getQuantity() < quantity) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
                    }
                    entity.setQuantity(entity.getQuantity() - quantity);
                    return pharmacyDrugRepository.save(entity);
                })
                .then();
    }
}
