package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.model.PharmacyDrug;

@Repository
public interface PharmacyDrugRepository extends ReactiveCrudRepository<PharmacyDrug, Long> {
    Flux<PharmacyDrug> findByPharmacyId(Long pharmacyId);
    Mono<PharmacyDrug> deleteByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);
    Flux<PharmacyDrug> findByDrugId(Long drugId);
    Mono<PharmacyDrug> findByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);

    @Modifying
    @Query("UPDATE pharmacy_drug SET count = :quantity WHERE pharmacy_id = :pharmacyId AND drug_id = :drugId")
    Mono<Integer> updateQuantity(Long pharmacyId, Long drugId, int quantity);
}
