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
import ru.coffeemachine.dto.request.AddCoffeeMachineRequest;
import ru.coffeemachine.dto.request.UpdateCoffeeMachineRequest;
import ru.coffeemachine.dto.response.CoffeeMachineResponse;

@Schema(name = "Контроллер кофемашины", description = "Операции с кофемашиной")
@RequestMapping("api/coffeemachines")
public interface CoffeeMachineApi {
    @Operation(summary = "Добавление кофемашины")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "кофемашина",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CoffeeMachineResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка при добавлении кофемашины")
    })
    @PostMapping
    ResponseEntity<CoffeeMachineResponse> addCoffeeMachine(@RequestBody @Valid AddCoffeeMachineRequest addCoffeeMachineRequest);

    @Operation(summary = "Получение кофемашины по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CoffeeMachineResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка при получении кофемашины по id"),
            @ApiResponse(responseCode = "404", description = "Кофемашина не найдена")
    })
    @GetMapping("/{coffee-machine-id}")
    ResponseEntity<CoffeeMachineResponse> getCoffeeMachineById(@PathVariable("coffee-machine-id")
                                                           @Valid @NotNull @Schema(description = "Идентификатор кофемашины", example = "1") Long coffeeMachineId);

    @Operation(summary = "Обновление данных о кофемашине")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "кофемашина",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CoffeeMachineResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Ошибка при обновлении данных о кофемашине"),
            @ApiResponse(responseCode = "404", description = "Кофемашины не существует")
    })
    @PutMapping
    ResponseEntity<CoffeeMachineResponse> updateCoffeeMachine(@RequestBody @Valid UpdateCoffeeMachineRequest updateCoffeeMachineRequest);
}
