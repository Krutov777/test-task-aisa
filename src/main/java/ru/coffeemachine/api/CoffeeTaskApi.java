package ru.coffeemachine.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.coffeemachine.dto.request.AddCoffeeTaskRequest;
import ru.coffeemachine.dto.response.CoffeeTaskResponse;

@Schema(name = "Контроллер заказов кофе", description = "Операции с заказами кофе")
@RequestMapping("api/coffeetasks")
public interface CoffeeTaskApi {
    @Operation(summary = "Добавление заказа кофе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "заказ кофе",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CoffeeTaskResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка при добавлении заказа кофе")
    })
    @PostMapping
    ResponseEntity<CoffeeTaskResponse> addCoffeeTask(@RequestBody @Valid AddCoffeeTaskRequest addCoffeeTaskRequest);

    @Operation(summary = "Получение заказа кофе по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CoffeeTaskResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка при получении кофемашины по id"),
            @ApiResponse(responseCode = "404", description = "Кофемашина не найдена")
    })
    @GetMapping("/{coffee-task-id}")
    ResponseEntity<CoffeeTaskResponse> getCoffeeTaskById(@PathVariable("coffee-task-id")
                                                               @Valid @NotNull @Schema(description = "Идентификатор заказа кофе", example = "1") Long coffeeTaskId);
}
