package model;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private final long id;
    private final String name;
    private final String address;
    private final List<PharmacyDrug> pharmacyDrugList;


    public Pharmacy(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pharmacyDrugList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<PharmacyDrug> getPharmacyDrugList() {
        return pharmacyDrugList;
    }

}
