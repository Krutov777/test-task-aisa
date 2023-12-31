package ru.coffeemachine.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coffeemachine.models.CoffeeTask;

import java.util.List;
import java.util.stream.Collectors;

import static ru.coffeemachine.constants.GlobalConstants.formatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Задание на приготовление кофе")
public class CoffeeTaskResponse {
    @Schema(description = "Статус задания", example = "COMPLETED")
    private String status;

    @Schema(description = "Количество чашек кофе", example = "12")
    private Integer numberCupsCoffee;

    @Schema(description = "Дата и время создания задачи", example = "2023-08-09 18:31:42")
    private String taskCreationDateTime;

    @Schema(description = "Дата и время начала приготовления кофе", example = "2023-08-09 18:31:42")
    private String cookingStartDateTime;

    @Schema(description = "Дата и время конца приготовления кофе", example = "2023-08-09 18:32:42")
    private String cookingCompletionDateTime;

    @Schema(description = "Идентификатор заказа", example = "1")
    private Long taskId;

    public static CoffeeTaskResponse from(CoffeeTask coffeeTask) {
        return CoffeeTaskResponse.builder()
                .taskId(coffeeTask.getId())
                .status(coffeeTask.getStatus().toString())
                .numberCupsCoffee(coffeeTask.getNumberCupsCoffee())
                .taskCreationDateTime(coffeeTask.getTaskCreationDateTime().format(formatter))
                .cookingStartDateTime(coffeeTask.getCookingStartDateTime() == null ? null : coffeeTask.getCookingStartDateTime().format(formatter))
                .cookingCompletionDateTime(coffeeTask.getCookingCompletionDateTime() == null ? null : coffeeTask.getCookingCompletionDateTime().format(formatter))
                .build();
    }

    public static List<CoffeeTaskResponse> from(List<CoffeeTask> coffeeTasks) {
        return coffeeTasks.stream().map(CoffeeTaskResponse::from).collect(Collectors.toList());
    }
}
