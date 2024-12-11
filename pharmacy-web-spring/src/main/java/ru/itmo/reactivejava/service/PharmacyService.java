package ru.itmo.reactivejava.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.dto.PharmacyDTO;
import ru.itmo.reactivejava.repository.PharmacyRepository;

@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Flux<PharmacyDTO> findAll() {
        return null;
    }

    public Mono<PharmacyDTO> findById(String id) {
        return null;
    }
}
