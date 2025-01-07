package ru.itmo.reactivejava.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.Drug;


@Repository
public interface DrugRepository extends ReactiveCrudRepository<Drug, Long> {

}
