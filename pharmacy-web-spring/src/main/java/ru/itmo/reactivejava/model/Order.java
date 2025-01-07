package ru.itmo.reactivejava.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    private Long id;
    private List<PharmacyDrug> drugs;
    private float amount;


    public Order(List<PharmacyDrug> drugs, float amount) {
        this.drugs = drugs;
        this.amount = amount;
    }
}
