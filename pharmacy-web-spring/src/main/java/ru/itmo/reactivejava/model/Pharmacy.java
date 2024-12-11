package ru.itmo.reactivejava.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

    @Id
    private long id;

    private final String name;

    private final String address;

//    private final List<PharmacyDrug> drugs = new ArrayList<>();

    public Pharmacy(/*long id,*/ String name, String address) {
//        this.id = id;
        this.name = name;
        this.address = address;
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

//    public List<PharmacyDrug> getDrugs() {
//        return drugs;
//    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
//                ", drugs=" + drugs +
                '}';
    }
}
