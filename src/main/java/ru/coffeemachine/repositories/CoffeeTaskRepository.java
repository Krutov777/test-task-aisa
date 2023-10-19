package ru.coffeemachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coffeemachine.models.CoffeeTask;

@Repository
public interface CoffeeTaskRepository extends JpaRepository<CoffeeTask, Long> {
}
