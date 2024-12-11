package ru.itmo.reactivejava.generator;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;

import java.math.BigDecimal;

@Component
public class PharmacyDrugGenerator {
    private final Faker faker = new Faker();
    private final PharmacyDrugRepository repository;

    public PharmacyDrugGenerator(PharmacyDrugRepository repository) {
        this.repository = repository;
    }

    public PharmacyDrug generatePharmacyDrug(long pharmacyId, long drugId) {
        BigDecimal price = BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1000)); // Цены от 100 до 1000

        PharmacyDrug pharmacyDrug = new PharmacyDrug(
                pharmacyId,
                drugId,
                price
        );

//        repository.save(pharmacyDrug).subscribe();

        return pharmacyDrug;
    }
}
