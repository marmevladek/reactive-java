package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.model.PharmacyDrug;

@Repository
public interface PharmacyDrugRepository extends ReactiveCrudRepository<PharmacyDrug, Long> {
    Flux<PharmacyDrug> findByPharmacyId(Long pharmacyId);
    Flux<PharmacyDrug> findByDrugId(Long drugId);
    Mono<PharmacyDrug> findByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);
}
