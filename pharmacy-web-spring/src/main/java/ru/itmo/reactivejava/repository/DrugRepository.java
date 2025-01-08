package ru.itmo.reactivejava.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.model.PharmacyDrug;


@Repository
public interface DrugRepository extends ReactiveCrudRepository<Drug, Long> {
    Mono<Drug> findById(Long id);
}
