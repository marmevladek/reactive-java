package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.reactivejava.service.GeneratorService;

@RestController
@RequestMapping("/api")
public class GeneratorController {
    private final GeneratorService generatorService;

    public GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generate() {
        return new ResponseEntity<>(generatorService.generate(), HttpStatus.CREATED);
    }
}
