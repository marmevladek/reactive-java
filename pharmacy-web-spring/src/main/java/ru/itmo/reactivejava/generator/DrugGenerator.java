package ru.itmo.reactivejava.generator;

import com.github.javafaker.Faker;
import org.instancio.Instancio;
import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.model.Drug;
import ru.itmo.reactivejava.model.PharmacologicalAction;
import ru.itmo.reactivejava.repository.PharmacologicalActionRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Component
public class DrugGenerator {
    private final Faker faker = new Faker();
    private final PharmacologicalActionGenerator pharmacologicalActionGenerator;

    public DrugGenerator(PharmacologicalActionGenerator pharmacologicalActionGenerator) {
        this.pharmacologicalActionGenerator = pharmacologicalActionGenerator;
    }

    public Drug generateDrug() {
        String name = faker.medical().medicineName();
        LocalDate manufactureDate = faker.date().past(1000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate expirationDate = manufactureDate.plusYears(faker.random().nextInt(1, 5));

        return new Drug(
                name,
                manufactureDate,
                expirationDate,
                pharmacologicalActionGenerator.generatePharmacologicalAction().getId()
        );
    }
}
