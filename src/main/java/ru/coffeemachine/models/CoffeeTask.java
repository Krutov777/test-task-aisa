package ru.coffeemachine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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
    private LocalDateTime taskCreationDateTime;

    @Column(name = "cooking_start_date_time", nullable = true)
    private LocalDateTime cookingStartDateTime;

    @Column(name = "cooking_completion_date_time" , nullable = true)
    private LocalDateTime cookingCompletionDateTime;

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
