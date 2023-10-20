package ru.coffeemachine.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Форма добавления кофемашины")
public class AddCoffeeTaskRequest {
    @NotNull(message = "Количество чашек кофе не может быть null")
    @Min(1)
    @Max(3)
    @Schema(description = "имя кофемашины", example = "bosh123")
    private Integer numberCupsCoffee;

    @NotNull
    @Schema(description = "Идентификатор кофемашины", example = "1")
    private Long id;
}
