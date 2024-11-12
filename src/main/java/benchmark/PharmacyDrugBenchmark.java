package benchmark;

import generator.GeneratorUtils;
import model.ActionType;
import model.Drug;
import model.Pharmacy;
import model.PharmacyDrug;
import org.openjdk.jmh.annotations.*;
import spliterator.PharmacyDrugSpliterator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@State(Scope.Thread)
@Fork(2)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
@OutputTimeUnit(TimeUnit.SECONDS)
public class PharmacyDrugBenchmark {

    private List<PharmacyDrug> pharmacyDrugList;

    @Param({"0", "1"})
    private long delay;

    @Param({"5000", "50000", "250000"})
    private int size;

    @Setup(Level.Iteration)
    public void setup() {
        pharmacyDrugList = generatePharmacyDrugList(size);
    }

    @Benchmark
    public Map<Pharmacy, Map<ActionType, Long>> sequentialStream() {
        return pharmacyDrugList.stream()
                .collect(Collectors.groupingBy(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingBy(
                                pd -> {
                                    for (int i = 0; i < 5000 * delay; i++) {
                                        Math.sqrt(i);
                                    }
                                    return pd.getDrug().getPharmacologicalAction().actionType();
                                },
                                Collectors.counting()
                        )
                ));
    }

    @Benchmark
    @Threads(4)
    public ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> parallelStream() {
        return pharmacyDrugList.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingByConcurrent(
                                pd -> {
                                    for (int i = 0; i < 5000 * delay; i++) {
                                        Math.sqrt(i);
                                    }
                                    return pd.getDrug().getPharmacologicalAction().actionType();
                                },
                                Collectors.counting()
                        )
                ));
    }

    @Benchmark
    @Threads(4)
    public ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> customSpliterator() {
        PharmacyDrugSpliterator spliterator = new PharmacyDrugSpliterator(pharmacyDrugList);

        return StreamSupport.stream(spliterator, true)
                .collect(Collectors.groupingByConcurrent(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingByConcurrent(
                                pd -> {
                                    for (int i = 0; i < 5000 * delay; i++) {
                                        Math.sqrt(i);
                                    }
                                    return pd.getDrug().getPharmacologicalAction().actionType();
                                },
                                Collectors.counting()
                        )
                ));
    }

    private List<PharmacyDrug> generatePharmacyDrugList(int size) {
        List<Drug> drugs = GeneratorUtils.generateDrugs(size);
        List<Pharmacy> pharmacies = GeneratorUtils.generatePharmacies(size / 10);
        return GeneratorUtils.generatePharmacyDrugs(size, pharmacies, drugs);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}