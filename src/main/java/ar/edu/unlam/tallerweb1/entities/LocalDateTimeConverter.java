package ar.edu.unlam.tallerweb1.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LocalDateTimeConverter {

    public static LocalDateTime convertToLocalDateTime(String date, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(date + ' ' + time + ":00", formatter);
    }

}
