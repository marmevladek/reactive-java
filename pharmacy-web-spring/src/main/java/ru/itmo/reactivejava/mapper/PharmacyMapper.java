package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.Pharmacy;
import ru.itmo.reactivejava.payload.request.PharmacyRequest;

public class PharmacyMapper {
    public static Pharmacy mapToPharmacy(PharmacyRequest pharmacyRequest) {
        return new Pharmacy(
                pharmacyRequest.getName(),
                pharmacyRequest.getAddress(),
                pharmacyRequest.getLatitude(),
                pharmacyRequest.getLongitude()
        );
    }
}
