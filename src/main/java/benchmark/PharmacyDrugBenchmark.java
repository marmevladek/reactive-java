package benchmark;

import generator.GeneratorUtils;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.ActionType;
import model.Drug;
import model.Pharmacy;
import model.PharmacyDrug;
import org.openjdk.jmh.annotations.*;
import spliterator.PharmacyDrugSpliterator;
import subscriber.CustomSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@State(Scope.Benchmark)
@Fork(2)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
@OutputTimeUnit(TimeUnit.SECONDS)
public class PharmacyDrugBenchmark {

    private List<PharmacyDrug> pharmacyDrugList;

    @Param({"0"})
    private long delay;

    @Param({"2000", "50000", "100000", "250000"})
    private int size;

    @Setup(Level.Iteration)
    public void setup() {
        pharmacyDrugList = generatePharmacyDrugList(size);
    }

    @Benchmark
    @Threads(4)
    public ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> parallelStream() {
        return pharmacyDrugList.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        PharmacyDrug::getPharmacy,
                        Collectors.groupingByConcurrent(
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

    @Benchmark
    @Threads(4)
    public Map<Pharmacy, Map<ActionType, Long>> rxJavaObservable() {
        return Observable.fromIterable(pharmacyDrugList)
                .groupBy(PharmacyDrug::getPharmacy)
                .flatMapSingle(pharmacyGroup ->
                        pharmacyGroup
                                .observeOn(Schedulers.io())
                                .groupBy(pharmacyDrug -> {
                                    try {
                                        Thread.sleep(delay);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                    return pharmacyDrug.getDrug().getPharmacologicalAction().actionType();
                                })
                                .observeOn(Schedulers.io())
                                .flatMapSingle(actionGroup ->
                                        actionGroup.count()
                                                .map(count -> Map.entry(actionGroup.getKey(), count))
                                )
                                .toList()
                                .map(entries -> {
                                    Map<ActionType, Long> actionTypeCountMap = new HashMap<>();
                                    entries.forEach(entry -> actionTypeCountMap.put(entry.getKey(), entry.getValue()));
                                    return Map.entry(pharmacyGroup.getKey(), actionTypeCountMap);
                                })
                )
                .toList()
                .blockingGet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Benchmark
    @Threads(4)
    public ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> rxJavaFlowable() {
        Flowable<PharmacyDrug> flowable = Flowable.fromIterable(pharmacyDrugList)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());

        CustomSubscriber subscriber = new CustomSubscriber(1000);

        flowable.subscribe(subscriber);

        return subscriber.getStatistics();

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