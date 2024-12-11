package ru.itmo.reactivejava.mapper;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.dto.DrugDTO;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.repository.PharmacologicalActionRepository;

import java.util.stream.Collectors;


@Component
public class DrugMapper {

    private final PharmacologicalActionRepository pharmacologicalActionRepository;
    private final PharmacyDrugMapper pharmacyDrugMapper;

    public DrugMapper(PharmacologicalActionRepository pharmacologicalActionRepository, PharmacyDrugMapper pharmacyDrugMapper) {
        this.pharmacologicalActionRepository = pharmacologicalActionRepository;
        this.pharmacyDrugMapper = pharmacyDrugMapper;
    }

    public Mono<DrugDTO> toDTO(Drug drug) {
        return pharmacologicalActionRepository.findById(drug.getPharmacologicalActionId())
                .map(pharmacologicalAction -> new DrugDTO(
                        drug.getId(),
                        drug.getName(),
                        drug.getManufactureDate(),
                        drug.getExpirationDate(),
                        pharmacologicalAction.getDescription(),
                        pharmacologicalAction.getActionType()
//                        drug.getPharmacies().stream()
//                                .map(pharmacyDrugMapper::toDTO)
//                                .collect(Collectors.toList())
                ))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("PharmacologicalAction not found for id: " + drug.getPharmacologicalActionId())));
    }
}
