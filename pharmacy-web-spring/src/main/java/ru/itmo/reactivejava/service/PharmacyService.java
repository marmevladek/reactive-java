package ru.itmo.reactivejava.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.PharmacyMapper;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.repository.DrugRepository;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;
import ru.itmo.reactivejava.repository.PharmacyRepository;

@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Mono<MessageResponse> addPharmacy(PharmacyRequest pharmacyRequest) {
        return pharmacyRepository.save(PharmacyMapper.mapToPharmacy(pharmacyRequest))
                .map(savePharmacy -> new MessageResponse("Аптека успешно создана"));
    }
}
