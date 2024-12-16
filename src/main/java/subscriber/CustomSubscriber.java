package subscriber;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import model.ActionType;
import model.Pharmacy;
import model.PharmacyDrug;
import org.reactivestreams.Subscription;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomSubscriber implements FlowableSubscriber<PharmacyDrug> {
    private Subscription subscription;
    private final ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> statistics = new ConcurrentHashMap<>();
    private final int batchSize;

    public CustomSubscriber(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        subscription.request(batchSize);
    }

    @Override
    public void onNext(PharmacyDrug pharmacyDrug) {
        statistics.computeIfAbsent(pharmacyDrug.getPharmacy(), k -> new ConcurrentHashMap<>())
                .merge(pharmacyDrug.getDrug().getPharmacologicalAction().actionType(), 1L, Long::sum);


        subscription.request(batchSize);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println("Error occurred: " + t.getMessage());
    }

    @Override
    public void onComplete() {
    }

    public ConcurrentMap<Pharmacy, ConcurrentMap<ActionType, Long>> getStatistics() {
        return statistics;
    }
}
