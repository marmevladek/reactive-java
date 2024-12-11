package ru.itmo.reactivejava.generator;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import ru.itmo.reactivejava.model.ActionType;
import ru.itmo.reactivejava.model.PharmacologicalAction;
import ru.itmo.reactivejava.repository.PharmacologicalActionRepository;

@Component
public class PharmacologicalActionGenerator {
    private final Faker faker = new Faker();
    private final PharmacologicalActionRepository repository;

    public PharmacologicalActionGenerator(PharmacologicalActionRepository repository) {
        this.repository = repository;
    }

    public PharmacologicalAction generatePharmacologicalAction() {
        String description = faker.lorem().sentence();
        ActionType actionType = ActionType.values()[faker.random().nextInt(ActionType.values().length)];

        PharmacologicalAction action = new PharmacologicalAction(
                description,
                actionType.name()
        );

        repository.save(action).subscribe();

        return action;
    }
}
