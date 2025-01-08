package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.PharmacyDrugRequest;

public class PharmacyDrugMapper {
    public static PharmacyDrug mapPharmacyDrug(PharmacyDrugRequest pharmacyDrugRequest) {
        return new PharmacyDrug(
                pharmacyDrugRequest.getPharmacyId(),
                pharmacyDrugRequest.getDrugId(),
                Math.toIntExact(pharmacyDrugRequest.getCount()),
                Math.toIntExact(pharmacyDrugRequest.getCount())
        );
    }
}
