package generator;

import com.github.javafaker.Faker;
import model.ActionType;
import model.PharmacologicalAction;
import org.instancio.Instancio;

import static org.instancio.Select.field;


public class PharmacologicalActionGenerator {
    private static final Faker faker = new Faker();

    public static PharmacologicalAction generatePharmacologicalAction() {
        return Instancio.of(PharmacologicalAction.class)
                .set(field(PharmacologicalAction.class, "actionType"), Instancio.of(ActionType.class).create())
                .set(field(PharmacologicalAction.class, "description"), faker.medical().symptoms())
                .create();
    }

}
