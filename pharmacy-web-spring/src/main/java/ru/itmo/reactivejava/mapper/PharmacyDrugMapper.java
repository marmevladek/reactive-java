package ru.itmo.reactivejava.mapper;

import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.dto.PharmacyDrugDTO;
import ru.itmo.reactivejava.model.PharmacyDrug;

@Component
public class PharmacyDrugMapper {

    public PharmacyDrugDTO toDTO(PharmacyDrug pharmacyDrug) {
        return new PharmacyDrugDTO(
                pharmacyDrug.getPharmacyId(),
                pharmacyDrug.getDrugId(),
                pharmacyDrug.getPrice()
        );
    }
}
