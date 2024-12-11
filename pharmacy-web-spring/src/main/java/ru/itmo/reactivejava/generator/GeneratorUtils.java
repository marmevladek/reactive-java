package ru.itmo.reactivejava.generator;

import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.model.PharmacologicalAction;
import ru.itmo.reactivejava.model.Pharmacy;
import ru.itmo.reactivejava.model.PharmacyDrug;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class GeneratorUtils {
    private final DrugGenerator drugGenerator;
    private final PharmacyGenerator pharmacyGenerator;
    private final PharmacyDrugGenerator pharmacyDrugGenerator;

    public GeneratorUtils(DrugGenerator drugGenerator, PharmacyGenerator pharmacyGenerator, PharmacyDrugGenerator pharmacyDrugGenerator) {
        this.drugGenerator = drugGenerator;
        this.pharmacyGenerator = pharmacyGenerator;
        this.pharmacyDrugGenerator = pharmacyDrugGenerator;
    }

    public List<Drug> generateDrugs(int count) {

        return IntStream.range(0, count)
                .mapToObj(i -> drugGenerator.generateDrug())
                .toList();
    }

    public  List<Pharmacy> generatePharmacies(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> pharmacyGenerator.generatePharmacy())
                .toList();
    }

    public List<PharmacyDrug> generatePharmacyDrugs(int count, List<Pharmacy> pharmacies, List<Drug> drugs) {
        return IntStream.range(0, count)
                .mapToObj(i -> pharmacyDrugGenerator.generatePharmacyDrug(
                        pharmacies.get(i % pharmacies.size()).getId(),
                        drugs.get(i % drugs.size()).getId()
                ))
                .toList();
    }
}
