package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.modelo.Reserve;

import java.util.List;

public interface IReserveService {

    List<Reserve> getReservesByUser(Long userId);

    List<CalendarReserve> getReservesForCalendar(Long userId);

    void makeReserve(CreateReserveDto reserveDto);

}
