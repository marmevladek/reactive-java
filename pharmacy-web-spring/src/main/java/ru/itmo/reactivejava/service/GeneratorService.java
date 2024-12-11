package ru.itmo.reactivejava.service;

import org.springframework.stereotype.Service;
import ru.itmo.reactivejava.generator.GeneratorUtils;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.model.Pharmacy;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.repository.DrugRepository;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;
import ru.itmo.reactivejava.repository.PharmacyRepository;

import java.util.List;

@Service
public class GeneratorService {
    private final GeneratorUtils generatorUtils;
    private final DrugRepository drugRepository;
    private final PharmacyRepository pharmacyRepository;
    private final PharmacyDrugRepository pharmacyDrugRepository;

    private static final int COUNT_OBJECTS = 1000;

    public GeneratorService(GeneratorUtils generatorUtils, DrugRepository drugRepository, PharmacyRepository pharmacyRepository, PharmacyDrugRepository pharmacyDrugRepository) {
        this.generatorUtils = generatorUtils;
        this.drugRepository = drugRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacyDrugRepository = pharmacyDrugRepository;
    }

    public String generate() {
        List<Drug> drugs = generatorUtils.generateDrugs(COUNT_OBJECTS);
        List<Pharmacy> pharmacies = generatorUtils.generatePharmacies(COUNT_OBJECTS / 10);
        drugRepository.saveAll(drugs).subscribe();
//        System.out.println("z");
//        pharmacyRepository.saveAll(pharmacies).subscribe();

//        List<PharmacyDrug> pharmacyDrugs = generatorUtils.generatePharmacyDrugs(COUNT_OBJECTS, pharmacies, drugs);

        drugRepository.saveAll(drugs).subscribe();
        pharmacyRepository.saveAll(pharmacies).subscribe();
//        pharmacyDrugRepository.saveAll(pharmacyDrugs).subscribe();

        return "Данные успешно сгенерированы!";
    }

}
