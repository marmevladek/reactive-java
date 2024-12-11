package ru.itmo.reactivejava.dto;

import ru.itmo.reactivejava.model.ActionType;

import java.time.LocalDate;
import java.util.List;

public record DrugDTO(
        long id,
        String name,
        LocalDate manufactureDate,
        LocalDate expirationDate,
        String description,
        String actionType
//        List<PharmacyDrugDTO> pharmacies
) {
}
