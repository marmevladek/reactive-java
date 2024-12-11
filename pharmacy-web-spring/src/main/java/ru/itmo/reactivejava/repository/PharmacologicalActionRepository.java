package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.PharmacologicalAction;

@Repository
public interface PharmacologicalActionRepository extends R2dbcRepository<PharmacologicalAction, Long> {
}
