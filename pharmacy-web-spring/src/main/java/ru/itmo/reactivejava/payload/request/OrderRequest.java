package ru.itmo.reactivejava.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.reactivejava.model.PharmacyDrug;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<PharmacyDrug> drugs;
    private float amount;

    public List<PharmacyDrug> getDrugs() {
        return drugs;
    }
}
