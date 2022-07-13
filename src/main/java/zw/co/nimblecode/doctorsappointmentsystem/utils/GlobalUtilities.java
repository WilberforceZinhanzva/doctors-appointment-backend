package zw.co.nimblecode.doctorsappointmentsystem.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GlobalUtilities {

    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static LocalDateTime parseDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, dateTimeFormatter());
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, dateFormatter());
    }
}
