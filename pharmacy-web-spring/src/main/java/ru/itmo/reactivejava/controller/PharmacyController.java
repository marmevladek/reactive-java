package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.service.DrugService;
import ru.itmo.reactivejava.service.PharmacyService;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<MessageResponse>> addPharmacy(@RequestBody PharmacyRequest pharmacyRequest) {
        return pharmacyService.addPharmacy(pharmacyRequest)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(ex -> {
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new MessageResponse("Error: " + ex.getMessage())));
                });
    }
}
