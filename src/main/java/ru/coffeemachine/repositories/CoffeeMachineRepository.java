package ru.coffeemachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coffeemachine.models.CoffeeMachine;

import java.util.List;

@Repository
public interface CoffeeMachineRepository extends JpaRepository<CoffeeMachine, Long> {
    List<CoffeeMachine> findAllByState(CoffeeMachine.State state);
}
