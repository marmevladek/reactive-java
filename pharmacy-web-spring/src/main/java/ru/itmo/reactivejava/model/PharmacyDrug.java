package ru.itmo.reactivejava.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pharmacy_drug")
public class PharmacyDrug {

    @Column("pharmacy_id")
    private Long pharmacyId;

    @Column("drug_id")
    private Long drugId;

    @Column("count")
    private int quantity;

    @Column("price")
    private float price;

    public PharmacyDrug() {
    }

    public PharmacyDrug(Long pharmacyId, Long drugId, int quantity, float price) {
        this.pharmacyId = pharmacyId;
        this.drugId = drugId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PharmacyDrug{" +
                "pharmacyId=" + pharmacyId +
                ", drugId=" + drugId +
                ", count=" + quantity +
                ", price=" + price +
                '}';
    }
}
