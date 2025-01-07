package ru.itmo.reactivejava.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pharmacy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pharmacy {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("address")
    private String address;

    public Pharmacy(String name, String address) {
        this.name = name;
        this.address = address;
    }

}

