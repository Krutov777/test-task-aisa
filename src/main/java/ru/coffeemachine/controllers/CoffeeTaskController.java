package ru.coffeemachine.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.coffeemachine.api.CoffeeTaskApi;
import ru.coffeemachine.dto.request.AddCoffeeTaskRequest;
import ru.coffeemachine.dto.response.CoffeeTaskResponse;
import ru.coffeemachine.services.CoffeeTaskService;

@RequiredArgsConstructor
@RestController
public class CoffeeTaskController implements CoffeeTaskApi {
    private final CoffeeTaskService coffeeTaskService;

    @Override
    public ResponseEntity<CoffeeTaskResponse> addCoffeeTask(AddCoffeeTaskRequest addCoffeeTaskRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(coffeeTaskService.addCoffeeTask(addCoffeeTaskRequest));
    }

    @Override
    public ResponseEntity<CoffeeTaskResponse> getCoffeeTaskById(Long coffeeTaskId) {
        return ResponseEntity.ok(coffeeTaskService.getCoffeeTaskById(coffeeTaskId));
    }
}
