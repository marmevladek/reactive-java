package spliterator;

import model.PharmacyDrug;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class PharmacyDrugSpliterator implements Spliterator<PharmacyDrug> {
    private final List<PharmacyDrug> list;
    private int current;
    private final int end;

    public PharmacyDrugSpliterator(List<PharmacyDrug> list) {
        this(list, 0, list.size());
    }

    private PharmacyDrugSpliterator(List<PharmacyDrug> list, int start, int end) {
        this.list = list;
        this.current = start;
        this.end = end;
    }

    @Override
    public boolean tryAdvance(Consumer<? super PharmacyDrug> action) {
        if (current < end) {
            PharmacyDrug item = list.get(current++);
            action.accept(item);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<PharmacyDrug> trySplit() {
        int size = end - current;
        if (size <= 1) {
            return null;
        }
        int mid = current + size / 2;
        Spliterator<PharmacyDrug> spliterator = new PharmacyDrugSpliterator(list, current, mid);
        current = mid;
        return spliterator;
    }

    @Override
    public long estimateSize() {
        return end - current;
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | SUBSIZED | IMMUTABLE | NONNULL;
    }
}
