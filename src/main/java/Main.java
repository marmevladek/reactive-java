import generator.GeneratorUtils;
import model.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {5000, 25000, 50000, 75000, 100000, 150000, 250000};
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " ЗАПУСК\n");
            for (int size : sizes) {

                List<Drug> drugs = GeneratorUtils.generateDrugs(size);
                List<Pharmacy> pharmacies = GeneratorUtils.generatePharmacies(size / 10);
                List<PharmacyDrug> pharmacyDrugs = GeneratorUtils.generatePharmacyDrugs(size, pharmacies, drugs);
                long delay = 0;


                System.out.println("\n############### " + i + " ЗАПУСК ####################################");
                System.out.println("Размер коллекции: " + size);
                System.out.println("-----------------------");

                Instant startTime = Instant.now();
                countThroughStreamSequential(pharmacyDrugs, delay);
                Instant endTime = Instant.now();

                System.out.println("Sequential Stream API : " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " мс." + " Задержка: " + delay);

                startTime = Instant.now();
                countThroughStreamParallel(pharmacyDrugs, delay);
                endTime = Instant.now();

                System.out.println("Parallel Stream API: " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " мс." + " Задержка: " + delay);
            }
        }


    }


    private static void countExpirationDrugsIteration(List<PharmacyDrug> pharmacyDrugs) {
        Map<Pharmacy, Map<ActionType, Integer>> mainMap = new HashMap<>();

        for (PharmacyDrug pd : pharmacyDrugs) {
            Pharmacy pharmacy = pd.getPharmacy();
            Drug drug = pd.fetchDrugWithDelay(0);
            ActionType actionType = drug.getPharmacologicalAction().actionType();

            Map<ActionType, Integer> secondMap = mainMap.computeIfAbsent(pharmacy, k -> new HashMap<>());

            secondMap.merge(actionType, 1, Integer::sum);
        }

    }



    private static void countThroughStreamSequential(List<PharmacyDrug> pharmacyDrugs, long delay) {
        Map<Pharmacy, Map<ActionType, Long>> mainMap = pharmacyDrugs.stream()
                .collect(Collectors.groupingBy(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingBy(
                                pd -> {
                                    try {
                                        Thread.sleep(delay);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        System.err.println("Задержка была прервана: " + e.getMessage());
                                    }
                                    return pd.getDrug().getPharmacologicalAction().actionType();
                                },
                                Collectors.counting()
                        )
                ));

    }

    private static void countThroughStreamParallel(List<PharmacyDrug> pharmacyDrugs, long delay) {
        Map<Pharmacy, Map<ActionType, Long>> mainMap = pharmacyDrugs.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingBy(
                                pd -> {
                                    try {
                                        Thread.sleep(delay);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                    return pd.getDrug().getPharmacologicalAction().actionType();
                                },
                                Collectors.counting()
                        )
                ));

    }

    private static void countThroughCustomCollector(List<PharmacyDrug> pharmacyDrugs) {
        Map<Pharmacy, Map<ActionType, Integer>> mainMap = pharmacyDrugs.stream()
                .collect(new PharmacyDrugCollector());
    }
}