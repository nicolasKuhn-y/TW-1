package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.modelo.Reserve;

import java.util.List;

public interface IReserveService {

    List<Reserve> getReservesByUser(Long userId);

}
