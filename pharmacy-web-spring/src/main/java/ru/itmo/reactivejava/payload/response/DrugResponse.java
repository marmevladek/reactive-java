package ru.itmo.reactivejava.payload.response;

import lombok.*;
import ru.itmo.reactivejava.service.DrugService;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugResponse {
    private Long id;
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private float price;
}
