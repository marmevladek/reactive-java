package ru.itmo.reactivejava.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrugResponse {
    private Long id;
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
}
