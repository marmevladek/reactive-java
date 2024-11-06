import model.ActionType;
import model.Pharmacy;
import model.PharmacyDrug;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PharmacyDrugCollector implements Collector<PharmacyDrug, Map<Pharmacy, Map<ActionType, Integer>>, Map<Pharmacy, Map<ActionType, Integer>>> {

    @Override
    public Supplier<Map<Pharmacy, Map<ActionType, Integer>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<Pharmacy, Map<ActionType, Integer>>, PharmacyDrug> accumulator() {
        return (map, pd) -> {
            Pharmacy pharmacy = pd.getPharmacy();
            ActionType actionType = pd.getDrug().getPharmacologicalAction().actionType();
            map.computeIfAbsent(pharmacy, k -> new HashMap<>())
                    .merge(actionType, 1, Integer::sum);
        };
    }

    @Override
    public BinaryOperator<Map<Pharmacy, Map<ActionType, Integer>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((pharmacy, actionMap) ->
                    map1.merge(pharmacy, actionMap, (m1, m2) -> {
                        m2.forEach((actionType, count) -> m1.merge(actionType, count, Integer::sum));
                        return m1;
                    }));
            return map1;
        };
    }

    @Override
    public Function<Map<Pharmacy, Map<ActionType, Integer>>, Map<Pharmacy, Map<ActionType, Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}