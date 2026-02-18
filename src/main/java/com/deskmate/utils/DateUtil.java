package com.deskmate.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {
	
    private static final DateTimeFormatter DT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter D = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {}

    public static LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input, DT);
    }

    public static LocalDate parseDate(String input) {
        return LocalDate.parse(input, D);
    }
}

