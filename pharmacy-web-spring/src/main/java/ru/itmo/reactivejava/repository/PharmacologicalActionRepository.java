package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.PharmacologicalAction;

@Repository
public interface PharmacologicalActionRepository extends ReactiveCrudRepository<PharmacologicalAction, Long> {
}
