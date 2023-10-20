package ru.coffeemachine.services;

import ru.coffeemachine.dto.request.AddCoffeeTaskRequest;
import ru.coffeemachine.dto.response.CoffeeTaskResponse;

public interface CoffeeTaskService {
    CoffeeTaskResponse addCoffeeTask(AddCoffeeTaskRequest addCoffeeTaskRequest);
    CoffeeTaskResponse getCoffeeTaskById(Long coffeeTaskId);
}
