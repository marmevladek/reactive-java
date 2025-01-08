package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.PharmacyDrugRequest;
import ru.itmo.reactivejava.payload.response.DrugResponse;
import ru.itmo.reactivejava.payload.response.PharmacyDrugResponse;

public class PharmacyDrugMapper {
    public static PharmacyDrug mapPharmacyDrug(PharmacyDrugRequest pharmacyDrugRequest) {
        return new PharmacyDrug(
                pharmacyDrugRequest.getPharmacyId(),
                pharmacyDrugRequest.getDrugId(),
                Math.toIntExact(pharmacyDrugRequest.getCount()),
                Math.toIntExact(pharmacyDrugRequest.getCount())
        );
    }

    public static PharmacyDrugResponse mapToPharmacyDrugResponse(PharmacyDrug pharmacyDrug, DrugResponse drugResponse) {
        return new PharmacyDrugResponse(
                drugResponse,
                pharmacyDrug.getQuantity(),
                pharmacyDrug.getInitial_quantity()
        );
    }
}
