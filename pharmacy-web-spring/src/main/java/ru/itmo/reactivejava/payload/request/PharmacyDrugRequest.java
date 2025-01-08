package ru.itmo.reactivejava.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PharmacyDrugRequest {
    @JsonProperty("pharmacy_id")
    private Long pharmacyId;

    @JsonProperty("drug_id")
    private Long drugId;

    @JsonProperty("count")
    private Long count;
}
