package model;

import java.time.LocalDate;

public class Drug {
    private final long id;
    private final String name;
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final PharmacologicalAction pharmacologicalAction;


    public Drug(long id, String name, LocalDate manufactureDate, LocalDate expirationDate, PharmacologicalAction pharmacologicalAction) {
        this.id = id;
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.pharmacologicalAction = pharmacologicalAction;
    }

    public long getId() {
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

    public PharmacologicalAction getPharmacologicalAction() {
        return pharmacologicalAction;
    }

}
