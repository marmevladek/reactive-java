package ru.itmo.reactivejava.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Drug {
    @Id
    private Long id;
    private final String name;
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final long pharmacologicalActionId;
//    private final List<PharmacyDrug> pharmacies = new ArrayList<>();
//    private final Set<PharmacyDrug> pharmacies = new HashSet<>();


    public Drug(/*Long id,*/ String name, LocalDate manufactureDate, LocalDate expirationDate, long pharmacologicalActionId) {
//        this.id = id;
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.pharmacologicalActionId = pharmacologicalActionId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public long getPharmacologicalActionId() {
        return pharmacologicalActionId;
    }

//    public List<PharmacyDrug> getPharmacies() {
//        return pharmacies;
//    }

//    public Set<PharmacyDrug> getPharmacies() {
//        return pharmacies;
//    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                ", pharmacologicalActionId=" + pharmacologicalActionId +
                '}';
    }
}
