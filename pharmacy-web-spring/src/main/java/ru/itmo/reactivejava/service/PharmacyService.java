package ru.itmo.reactivejava.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.PharmacyMapper;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.payload.response.PharmacyResponse;
import ru.itmo.reactivejava.repository.PharmacyRepository;

import java.util.List;

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

    public Mono<List<PharmacyResponse>> getPharmacies() {
        return pharmacyRepository.findAll()
                .map(PharmacyMapper::mapToPharmacyResponse)
                .collectList();
    }

    public Mono<PharmacyResponse> getPharmacyById(Long id) {
        return pharmacyRepository.findById(id)
                .map(PharmacyMapper::mapToPharmacyResponse)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Аптека не найдена"
                )));
    }
}
