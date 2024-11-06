import generator.GeneratorUtils;
import model.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] sizes = {5000, 50000, 250000};
        for (int size : sizes) {

            List<Drug> drugs = GeneratorUtils.generateDrugs(size);
            List<Pharmacy> pharmacies = GeneratorUtils.generatePharmacies(size / 10);
            List<PharmacyDrug> pharmacyDrugs = GeneratorUtils.generatePharmacyDrugs(size, pharmacies, drugs);


            System.out.println("\n###############################################################");
            System.out.println("Размер коллекции: " + size);
            System.out.println("-----------------------");

            Instant startTime = Instant.now();
            countExpirationDrugsIteration(pharmacyDrugs);
            Instant endTime = Instant.now();

            System.out.println("Iteration: " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " мс");


            startTime = Instant.now();
            countThroughStream(pharmacyDrugs);
            endTime = Instant.now();

            System.out.println("Stream API : " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " мс");


            startTime = Instant.now();
            countThroughCustomCollector(pharmacyDrugs);
            endTime = Instant.now();

            System.out.println("Custom Collector: " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " мс");
        }

    }


    private static void countExpirationDrugsIteration(List<PharmacyDrug> pharmacyDrugs) {
        Map<Pharmacy, Map<ActionType, Integer>> mainMap = new HashMap<>();

        for (PharmacyDrug pd : pharmacyDrugs) {
            Pharmacy pharmacy = pd.getPharmacy();
            ActionType actionType = pd.getDrug().getPharmacologicalAction().actionType();

            Map<ActionType, Integer> secondMap = mainMap.computeIfAbsent(pharmacy, k -> new HashMap<>());

            secondMap.merge(actionType, 1, Integer::sum);
        }

    }

    private static void countThroughStream(List<PharmacyDrug> pharmacyDrugs) {
        Map<Pharmacy, Map<ActionType, Long>> mainMap = pharmacyDrugs.stream()
                .collect(Collectors.groupingBy(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingBy(
                                pd -> pd.getDrug().getPharmacologicalAction().actionType(),
                                Collectors.counting()
                        )
                ));
    }

    private static void countThroughCustomCollector(List<PharmacyDrug> pharmacyDrugs) {
        Map<Pharmacy, Map<ActionType, Integer>> mainMap = pharmacyDrugs.stream()
                .collect(new PharmacyDrugCollector());
    }
}