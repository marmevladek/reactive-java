package model;

import java.math.BigDecimal;

public class PharmacyDrug {
    private final Pharmacy pharmacy;
    private final Drug drug;
    private final BigDecimal price;

    public PharmacyDrug(Pharmacy pharmacy, Drug drug, BigDecimal price) {
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.price = price;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Drug getDrug() {
        return drug;
    }

    public Drug fetchDrugWithDelay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Задержка была прервана: " + e.getMessage());
        }
        return getDrug();
    }

    public BigDecimal getPrice() {
        return price;
    }
}
