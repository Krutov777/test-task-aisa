package ru.coffeemachine.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coffeemachine.models.CoffeeMachine;
import ru.coffeemachine.models.CoffeeTask;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Кофемашина")
public class CoffeeMachineResponse {
    @Schema(description = "имя кофемашины", example = "bosh123")
    private String name;

    @Schema(description = "Состояние кофемашины", example = "ON")
    private String state;

    @Schema(description = "Количество приготовленных чашек кофе", example = "7")
    private Integer numberCupsCoffeePrepared;

    @Schema(description = "Идентификатор кофемашины", example = "1")
    private Long id;

    @Schema(description = "Список заказов кофе")
    private List<CoffeeTask> coffeeTasks;

    public static CoffeeMachineResponse from(CoffeeMachine coffeeMachine) {
        return CoffeeMachineResponse.builder()
                .id(coffeeMachine.getId())
                .name(coffeeMachine.getName())
                .state(coffeeMachine.getState().toString())
                .numberCupsCoffeePrepared(coffeeMachine.getNumberCupsCoffeePrepared())
                .coffeeTasks(coffeeMachine.getCoffeeTasks())
                .build();
    }
}
