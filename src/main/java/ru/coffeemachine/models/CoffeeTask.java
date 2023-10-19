package ru.coffeemachine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffee_task")
@Entity
public class CoffeeTask {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "number_cups_coffee")
    private Integer numberCupsCoffee;

    @Column(name = "task_creation_date_time")
    private Date taskCreationDateTime;

    @Column(name = "cooking_start_date_time")
    private Date cookingStartDateTime;

    @Column(name = "cooking_completion_date_time")
    private Date cookingCompletionDateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coffee_machine_id")
    private CoffeeMachine coffeeMachine;

    public enum Status {
        AWAITS, COMPLETED, IN_WORK
    }
}
