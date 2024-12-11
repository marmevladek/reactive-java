package ru.itmo.reactivejava.generator;

import com.github.javafaker.Faker;
import org.instancio.Instancio;
import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.model.Pharmacy;

@Component
public class PharmacyGenerator {
    private final Faker faker = new Faker();

    public Pharmacy generatePharmacy() {
//        long id = Instancio.create(long.class);
        String name = faker.company().name();
        String address = faker.address().streetAddress();

        return new Pharmacy(
//                id,
                name,
                address
        );
    }
}
