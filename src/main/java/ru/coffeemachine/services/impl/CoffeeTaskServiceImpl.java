package ru.coffeemachine.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.coffeemachine.dto.request.AddCoffeeTaskRequest;
import ru.coffeemachine.dto.response.CoffeeTaskResponse;
import ru.coffeemachine.exceptions.CoffeeMachineNotFoundException;
import ru.coffeemachine.exceptions.CoffeeTaskNotFoundException;
import ru.coffeemachine.models.CoffeeMachine;
import ru.coffeemachine.models.CoffeeTask;
import ru.coffeemachine.repositories.CoffeeMachineRepository;
import ru.coffeemachine.repositories.CoffeeTaskRepository;
import ru.coffeemachine.services.CoffeeTaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.coffeemachine.constants.GlobalConstants.COFFEE_MACHINE_NOT_FOUND;
import static ru.coffeemachine.constants.GlobalConstants.COFFEE_TASK_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CoffeeTaskServiceImpl implements CoffeeTaskService {
    private final CoffeeMachineRepository coffeeMachineRepository;
    private final CoffeeTaskRepository coffeeTaskRepository;

    @Override
    public CoffeeTaskResponse addCoffeeTask(AddCoffeeTaskRequest addCoffeeTaskRequest) {
        return CoffeeTaskResponse.from(
                coffeeTaskRepository.save(
                        CoffeeTask.builder()
                                .coffeeMachine(coffeeMachineRepository.findById(addCoffeeTaskRequest.getId()).orElseThrow(() -> new CoffeeMachineNotFoundException(COFFEE_MACHINE_NOT_FOUND)))
                                .numberCupsCoffee(addCoffeeTaskRequest.getNumberCupsCoffee())
                                .status(CoffeeTask.Status.AWAITS)
                                .taskCreationDateTime(LocalDateTime.now())
                                .cookingCompletionDateTime(null)
                                .cookingStartDateTime(null)
                                .build()
                )
        );
    }

    @Override
    public CoffeeTaskResponse getCoffeeTaskById(Long coffeeTaskId) {
        return CoffeeTaskResponse.from(coffeeTaskRepository.findById(coffeeTaskId).orElseThrow(() -> new CoffeeTaskNotFoundException(COFFEE_TASK_NOT_FOUND)));
    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void orderExecution() {
        List<CoffeeMachine> coffeeMachines = coffeeMachineRepository.findAllByState(CoffeeMachine.State.ON);
        coffeeMachines.forEach(coffeeMachine -> {
            List<CoffeeTask> coffeeTasks = coffeeMachine.getCoffeeTasks()
                    .stream()
                    .filter(coffeeTask -> List.of(CoffeeTask.Status.AWAITS, CoffeeTask.Status.IN_WORK).contains(coffeeTask.getStatus()))
                    .collect(Collectors.toList());
            coffeeTasks.forEach(coffeeTask -> {
                var coffeeMachineWithTask = coffeeTask.getCoffeeMachine();
                coffeeTask.setCookingStartDateTime(LocalDateTime.now());
                try {
                    Thread.sleep(coffeeTask.getNumberCupsCoffee() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                coffeeTask.setCookingCompletionDateTime(LocalDateTime.now());
                coffeeTask.setStatus(CoffeeTask.Status.COMPLETED);
                coffeeMachineWithTask.setNumberCupsCoffeePrepared(coffeeMachineWithTask.getNumberCupsCoffeePrepared() + coffeeTask.getNumberCupsCoffee());
                coffeeTask.setCoffeeMachine(coffeeMachineWithTask);
            });
            coffeeTaskRepository.saveAll(coffeeTasks);
        });
    }
}
