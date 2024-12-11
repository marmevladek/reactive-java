package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.dto.DrugDTO;
import ru.itmo.reactivejava.service.DrugService;

@RestController
@RequestMapping("/api")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("/drugs")
    @ResponseStatus(HttpStatus.OK)
    public Flux<DrugDTO> findAll() {
        return drugService.findAll();
    }

    @GetMapping("/drugs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<DrugDTO> findById(@PathVariable("id") long id) {
        return drugService.findById(id);
    }
}
