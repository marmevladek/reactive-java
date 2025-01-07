package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.payload.request.DrugRequest;


public class DrugMapper {

    public static Drug mapToDrug(DrugRequest drugRequest) {
        return new Drug(
                drugRequest.getName(),
                drugRequest.getManufactureDate(),
                drugRequest.getExpirationDate()
        );
    }
}
