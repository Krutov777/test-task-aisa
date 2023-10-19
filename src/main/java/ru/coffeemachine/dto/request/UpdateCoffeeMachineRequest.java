package ru.coffeemachine.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Форма обновления данных о кофемашине")
public class UpdateCoffeeMachineRequest {
    @NotNull
    @Schema(description = "Идентификатор кофемашины", example = "1")
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 1, max = 20, message = "минимальный размер имени {min}, максимальный - {max}")
    @Schema(description = "имя кофемашины", example = "bosh123")
    private String name;

    @NotBlank(message = "Состояние кофемашины не может быть пустым")
    @Schema(description = "Состояние кофемашины", example = "ON")
    private String state;
}
