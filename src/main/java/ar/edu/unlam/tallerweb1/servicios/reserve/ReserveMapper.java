package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ReserveMapper {
    public CalendarReserve mapReserveToCalendarReserve(Reserve reserve) {
        return new CalendarReserve(
                reserve.getHospital().getName(),
                reserve.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
