package ru.itmo.reactivejava.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

public class PharmacologicalAction {

    @Id
    private long id;

    private final String description;

    private final String actionType;

    public PharmacologicalAction(String description, String actionType) {
        this.description = description;
        this.actionType = actionType;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return "PharmacologicalAction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", actionType=" + actionType +
                '}';
    }
}
