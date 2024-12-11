package ru.itmo.reactivejava.dto;

import java.util.List;

public record PharmacyDTO(
        long id,
        String name,
        String address,
        List<PharmacyDrugDTO> drugs
) {
}
