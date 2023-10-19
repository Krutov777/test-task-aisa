package ru.coffeemachine.services;

import ru.coffeemachine.dto.request.AddCoffeeMachineRequest;
import ru.coffeemachine.dto.request.UpdateCoffeeMachineRequest;
import ru.coffeemachine.dto.response.CoffeeMachineResponse;

public interface CoffeeMachineService {
    CoffeeMachineResponse addCoffeeMachine(AddCoffeeMachineRequest addCoffeeMachineRequest);
    CoffeeMachineResponse getCoffeeMachineById(Long coffeeMachineId);
    CoffeeMachineResponse updateCoffeeMachine(UpdateCoffeeMachineRequest updateCoffeeMachineRequest);
}
