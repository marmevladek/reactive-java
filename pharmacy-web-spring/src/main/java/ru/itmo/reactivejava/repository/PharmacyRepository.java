package ru.itmo.reactivejava.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.Pharmacy;

@Repository
public interface PharmacyRepository extends ReactiveCrudRepository<Pharmacy, Long> {
}
