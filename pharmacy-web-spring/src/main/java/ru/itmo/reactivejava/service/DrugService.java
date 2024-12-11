package ru.itmo.reactivejava.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.dto.DrugDTO;
import ru.itmo.reactivejava.mapper.DrugMapper;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.repository.DrugRepository;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    public DrugService(DrugRepository drugRepository, DrugMapper drugMapper) {
        this.drugRepository = drugRepository;
        this.drugMapper = drugMapper;
    }


    public Flux<DrugDTO> findAll() {
        return drugRepository.findAll().flatMap(drugMapper::toDTO);
    }

    public Mono<DrugDTO> findById(long id) {
        return drugRepository.findById(id)
                .flatMap(drugMapper::toDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Drug not found for id: " + id))); // Обработка случая, когда лекарство не найдено
    }
}
