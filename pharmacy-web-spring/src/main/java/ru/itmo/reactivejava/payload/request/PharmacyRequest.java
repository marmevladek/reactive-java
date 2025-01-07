package ru.itmo.reactivejava.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PharmacyRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;
}