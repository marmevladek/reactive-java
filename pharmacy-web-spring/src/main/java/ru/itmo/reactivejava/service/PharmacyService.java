package ru.itmo.reactivejava.service;

import com.github.javafaker.Faker;
import org.instancio.Instancio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.mapper.PharmacyMapper;
import ru.itmo.reactivejava.model.Pharmacy;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.payload.response.PharmacyResponse;
import ru.itmo.reactivejava.repository.PharmacyRepository;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    private static final Faker faker = new Faker();

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Mono<MessageResponse> addPharmacy(PharmacyRequest pharmacyRequest) {
        return pharmacyRepository.save(PharmacyMapper.mapToPharmacy(pharmacyRequest))
                .map(savePharmacy -> new MessageResponse("Аптека успешно создана"));
    }

    public Flux<MessageResponse> generatePharmacies(int count) {
        List<Pharmacy> pharmacies = IntStream
                .range(0, count)
                .mapToObj(i -> {
                    Pharmacy pharmacy = new Pharmacy();
                    pharmacy.setName(faker.medical().hospitalName());
                    pharmacy.setAddress(faker.address().streetAddress());
                    return pharmacy;
                })
                .toList();

        return pharmacyRepository.saveAll(pharmacies)
                .map(response -> new MessageResponse("Аптека успешно сгенерирована"));

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
