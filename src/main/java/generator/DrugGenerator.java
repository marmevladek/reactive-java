package generator;

import com.github.javafaker.Faker;
import model.Drug;
import org.instancio.Instancio;


import static org.instancio.Select.field;

public class DrugGenerator {
    private static final Faker faker = new Faker();

    public static Drug generateDrug() {
        return Instancio.of(Drug.class)
                .set(field(Drug.class, "id"), Instancio.create(long.class))
                .set(field(Drug.class, "name"), faker.medical().medicineName())
                .generate(field(Drug.class, "manufactureDate"), gen -> gen.temporal().localDate().past())
                .generate(field(Drug.class, "expirationDate"), gen -> gen.temporal().localDate().future())
                .set(field(Drug.class, "pharmacologicalAction"), PharmacologicalActionGenerator.generatePharmacologicalAction())
                .create();
    }
}

