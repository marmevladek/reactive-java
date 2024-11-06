package generator;

import model.Drug;
import model.Pharmacy;
import model.PharmacyDrug;

import java.util.List;
import java.util.stream.IntStream;

public class GeneratorUtils {

    public static List<Drug> generateDrugs(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> DrugGenerator.generateDrug())
                .toList();
    }

    public static List<Pharmacy> generatePharmacies(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> PharmacyGenerator.generatePharmacy())
                .toList();
    }

    public static List<PharmacyDrug> generatePharmacyDrugs(int count, List<Pharmacy> pharmacies, List<Drug> drugs) {
        return IntStream.range(0, count)
                .mapToObj(i -> PharmacyDrugGenerator.generatePharmacyDrug(
                        pharmacies.get(i % pharmacies.size()),
                        drugs.get(i % drugs.size())
                ))
                .toList();
    }
}
