package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.dto.PharmacyDTO;
import ru.itmo.reactivejava.service.PharmacyService;

@RestController
@RequestMapping("/api")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacies")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PharmacyDTO> findAll() {
        return pharmacyService.findAll();
    }

    @GetMapping("/pharmacies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PharmacyDTO> findById(@PathVariable("id") String id) {
        return pharmacyService.findById(id);
    }
}
