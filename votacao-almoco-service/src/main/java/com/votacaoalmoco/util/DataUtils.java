package com.votacaoalmoco.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class DataUtils {
    public static boolean isSegundaFeira(LocalDate localDate) {
        return DayOfWeek.MONDAY.equals(localDate.getDayOfWeek());
    }
}
