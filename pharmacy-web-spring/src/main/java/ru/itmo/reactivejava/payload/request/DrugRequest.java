package ru.itmo.reactivejava.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class DrugRequest {
    private String name;

    @JsonProperty("manufacture_date")
    private LocalDate manufactureDate;

    @JsonProperty("expiration_date")
    private LocalDate expirationDate;

    @JsonProperty("pharmacological_action_id")
    private Long pharmacologicalActionId;

    @Override
    public String toString() {
        return name + " " + manufactureDate + " " + expirationDate + " " + pharmacologicalActionId;
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
}
