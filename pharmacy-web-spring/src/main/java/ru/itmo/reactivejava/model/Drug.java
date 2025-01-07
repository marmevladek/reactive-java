package ru.itmo.reactivejava.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("drug")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column("name")
    private String name;

    @Column("manufacture_date")
    private LocalDate manufactureDate;

    @Column("expiration_date")
    private LocalDate expirationDate;

    public Drug(String name, LocalDate manufactureDate, LocalDate expirationDate) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

}
