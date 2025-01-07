package ru.itmo.reactivejava.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pharmacy_drug")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PharmacyDrug {

    @Column("pharmacy_id")
    private Long pharmacyId;

    @Column("drug_id")
    private Long drugId;

    @Column("count")
    private int quantity;

    @Column("price")
    private float price;
}
