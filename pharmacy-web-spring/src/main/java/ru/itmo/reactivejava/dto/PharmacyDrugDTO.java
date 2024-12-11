package ru.itmo.reactivejava.dto;

import java.math.BigDecimal;

public record PharmacyDrugDTO(
        long pharmacyId,
        long drugId,
        BigDecimal price
) {
}
