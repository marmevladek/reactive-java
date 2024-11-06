package generator;

import com.github.javafaker.Faker;
import model.Pharmacy;
import org.instancio.Instancio;

import static org.instancio.Select.field;

public class PharmacyGenerator {
    private static final Faker faker = new Faker();

    public static Pharmacy generatePharmacy() {
        return Instancio.of(Pharmacy.class)
                .set(field(Pharmacy.class, "id"), Instancio.create(long.class))
                .set(field(Pharmacy.class, "name"), faker.medical().hospitalName())
                .set(field(Pharmacy.class, "address"), faker.address().streetAddress())
                .create();
    }
}
