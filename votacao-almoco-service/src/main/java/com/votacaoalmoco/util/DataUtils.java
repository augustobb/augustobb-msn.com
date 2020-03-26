package com.votacaoalmoco.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class DataUtils {

    private DataUtils() {
    }

    public static boolean isSabado(LocalDate localDate) {
        return DayOfWeek.SATURDAY.equals(localDate.getDayOfWeek());
    }

    public static boolean isDomingo(LocalDate localDate) {
        return DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek());
    }

    public static boolean isSegundaFeira(LocalDate localDate) {
        return DayOfWeek.MONDAY.equals(localDate.getDayOfWeek());
    }
}
