package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.PharmacyDrug;

@Repository
public interface PharmacyDrugRepository extends R2dbcRepository<PharmacyDrug, Long> {
}
