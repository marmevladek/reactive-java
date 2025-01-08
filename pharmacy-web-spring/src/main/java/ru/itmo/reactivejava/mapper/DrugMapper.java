package ru.itmo.reactivejava.mapper;

import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.payload.request.DrugRequest;
import ru.itmo.reactivejava.payload.response.DrugResponse;


public class DrugMapper {

    public static Drug mapToDrug(DrugRequest drugRequest) {
        return new Drug(
                drugRequest.getName(),
                drugRequest.getManufactureDate(),
                drugRequest.getExpirationDate(),
                drugRequest.getPrice()
        );
    }

    public static DrugResponse mapToDrugResponse(Drug drug) {
        return new DrugResponse(
                drug.getId(),
                drug.getName(),
                drug.getManufactureDate(),
                drug.getExpirationDate()
        );
    }
}
