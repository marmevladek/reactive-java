package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.Pharmacy;

@Repository
public interface PharmacyRepository extends R2dbcRepository<Pharmacy, Long> {
}
