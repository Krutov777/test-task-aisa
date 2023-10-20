package ru.coffeemachine.constants;

import java.time.format.DateTimeFormatter;

public class GlobalConstants {
    public static final String COFFEE_MACHINE_NOT_FOUND = "Кофемашина не найдена";
    public static final String COFFEE_TASK_NOT_FOUND = "Заказ кофе не найден";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
