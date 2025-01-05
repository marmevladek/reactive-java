package ru.itmo.reactivejava.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table("drugs")
public class Drug {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("manufacture_date")
    private LocalDate manufactureDate;

    @Column("expiration_date")
    private LocalDate expirationDate;

    @Column("pharmacological_action_id")
    private Long pharmacologicalActionId;

    public Drug() {
    }

    public Drug(String name, LocalDate manufactureDate, LocalDate expirationDate, Long pharmacologicalActionId) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.pharmacologicalActionId = pharmacologicalActionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getPharmacologicalActionId() {
        return pharmacologicalActionId;
    }

    public void setPharmacologicalActionId(Long pharmacologicalActionId) {
        this.pharmacologicalActionId = pharmacologicalActionId;
    }

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
