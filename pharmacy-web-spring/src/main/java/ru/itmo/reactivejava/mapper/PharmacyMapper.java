package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.Pharmacy;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;
import ru.itmo.reactivejava.payload.response.PharmacyResponse;

public class PharmacyMapper {
    public static Pharmacy mapToPharmacy(PharmacyRequest pharmacyRequest) {
        return new Pharmacy(
                pharmacyRequest.getName(),
                pharmacyRequest.getAddress()
        );
    }

    public static PharmacyResponse mapToPharmacyResponse(Pharmacy pharmacy) {
        return new PharmacyResponse(
                pharmacy.getId(),
                pharmacy.getName()
        );
    }
}
