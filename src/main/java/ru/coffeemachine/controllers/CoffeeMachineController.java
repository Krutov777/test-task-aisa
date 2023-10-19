package ru.coffeemachine.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.coffeemachine.api.CoffeeMachineApi;
import ru.coffeemachine.dto.request.AddCoffeeMachineRequest;
import ru.coffeemachine.dto.request.UpdateCoffeeMachineRequest;
import ru.coffeemachine.dto.response.CoffeeMachineResponse;
import ru.coffeemachine.services.CoffeeMachineService;

@RequiredArgsConstructor
@RestController
public class CoffeeMachineController implements CoffeeMachineApi {
    private final CoffeeMachineService coffeeMachineService;

    @Override
    public ResponseEntity<CoffeeMachineResponse> addCoffeeMachine(AddCoffeeMachineRequest addCoffeeMachineRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(coffeeMachineService.addCoffeeMachine(addCoffeeMachineRequest));
    }

    @Override
    public ResponseEntity<CoffeeMachineResponse> getCoffeeMachineById(Long coffeeMachineId) {
        return ResponseEntity.ok(coffeeMachineService.getCoffeeMachineById(coffeeMachineId));
    }

    @Override
    public ResponseEntity<CoffeeMachineResponse> updateCoffeeMachine(UpdateCoffeeMachineRequest updateCoffeeMachineRequest) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(coffeeMachineService.updateCoffeeMachine(updateCoffeeMachineRequest));
    }
}
