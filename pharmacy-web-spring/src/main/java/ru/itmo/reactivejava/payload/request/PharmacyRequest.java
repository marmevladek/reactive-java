package ru.itmo.reactivejava.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


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