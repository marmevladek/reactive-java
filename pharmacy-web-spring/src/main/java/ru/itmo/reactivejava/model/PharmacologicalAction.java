package ru.itmo.reactivejava.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("pharmacological_actions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PharmacologicalAction {

    @Id
    private Long id;

    @Column("description")
    private String description;

    @Column("action_type")
    private ActionType actionType;

    public PharmacologicalAction(String description, ActionType actionType) {
        this.description = description;
        this.actionType = actionType;
    }
}
