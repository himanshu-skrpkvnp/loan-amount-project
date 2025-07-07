package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String getNextMonthMidDate(String monthYearString) {
        try {

            logger.debug("Recieved Dateformat ::" + monthYearString );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
            YearMonth ym = YearMonth.parse(monthYearString.trim(), formatter);

            LocalDate nextMonthMid = ym.plusMonths(1).atDay(15);

            return nextMonthMid.format(DateTimeFormatter.ISO_LOCAL_DATE); // "2024-05-15"
        } catch (Exception e) {
            logger.error("Parsing error in recieved Dateformat" + e.getMessage() );
            return "2024-05-15"; // Fallback value in case of parsing error
        }
    }
}
