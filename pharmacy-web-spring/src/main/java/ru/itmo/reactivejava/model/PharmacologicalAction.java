package ru.itmo.reactivejava.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("pharmacological_actions")
public class PharmacologicalAction {

    @Id
    private Long id;

    @Column("description")
    private String description;

    @Column("action_type")
    private ActionType actionType;

    public PharmacologicalAction() {
        // Default constructor for R2DBC
    }

    public PharmacologicalAction(String description, ActionType actionType) {
        this.description = description;
        this.actionType = actionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
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
