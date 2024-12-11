package ru.itmo.reactivejava.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.reactivejava.model.Drug;


@Repository
public interface DrugRepository extends R2dbcRepository<Drug, Long> {

}
