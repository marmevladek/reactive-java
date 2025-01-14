package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.payload.response.PharmacyResponse;
import ru.itmo.reactivejava.service.PharmacyService;

import java.util.List;

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

    @PostMapping("/generate")
    public Flux<MessageResponse> generatePharmacies(@RequestParam int count) {
        return pharmacyService.generatePharmacies(count);
    }



    @GetMapping("/")
    public Mono<ResponseEntity<List<PharmacyResponse>>> getPharmacies() {
        return pharmacyService.getPharmacies()
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PharmacyResponse>> getPharmacyById(@PathVariable("id") Long id) {
        return pharmacyService.getPharmacyById(id)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
