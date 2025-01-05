package ru.itmo.reactivejava.model;

import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("orders")
public class Order {

    private Long id;
    private List<PharmacyDrug> drugs;
    private float amount;

    public Order() {}

    public Order(List<PharmacyDrug> drugs, float amount) {
        this.drugs = drugs;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PharmacyDrug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<PharmacyDrug> drugs) {
        this.drugs = drugs;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", drugs=" + drugs +
                ", amount=" + amount +
                '}';
    }
}
