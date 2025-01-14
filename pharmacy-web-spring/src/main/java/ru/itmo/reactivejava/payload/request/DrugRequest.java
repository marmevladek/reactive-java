package ru.itmo.reactivejava.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DrugRequest {

    private String name;

    @JsonProperty("manufacture_date")
    private LocalDate manufactureDate;

    @JsonProperty("expiration_date")
    private LocalDate expirationDate;
    
    @JsonProperty("price")
    private float price;
}
