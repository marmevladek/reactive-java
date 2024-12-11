package ru.itmo.reactivejava.mapper;

import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.dto.PharmacyDTO;
import ru.itmo.reactivejava.model.Pharmacy;

@Component
public class PharmacyMapper {
    private final PharmacyDrugMapper pharmacyDrugMapper;

    public PharmacyMapper(PharmacyDrugMapper pharmacyDrugMapper) {
        this.pharmacyDrugMapper = pharmacyDrugMapper;
    }

//    public PharmacyDTO toDTO(Pharmacy pharmacy) {
//
//    }
}
