package ru.coffeemachine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffee_machine")
@Entity
public class CoffeeMachine {
    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Column(name = "number_cups_coffee_prepared")
    private Integer numberCupsCoffeePrepared;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "coffeeMachine")
    private List<CoffeeTask> coffeeTasks;

    public enum State {
        ON, OFF
    }
}
