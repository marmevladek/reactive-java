package ru.itmo.reactivejava.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

public class PharmacyDrug {


    private final long pharmacyId;

    private final long drugId;

    private final BigDecimal price;

    public PharmacyDrug(long pharmacyId, long drugId, BigDecimal price) {
        this.pharmacyId = pharmacyId;
        this.drugId = drugId;
        this.price = price;
    }


    public long getPharmacyId() {
        return pharmacyId;
    }

    public long getDrugId() {
        return drugId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PharmacyDrug{" +
                "pharmacyId=" + pharmacyId +
                ", drugId=" + drugId +
                ", price=" + price +
                '}';
    }
}
