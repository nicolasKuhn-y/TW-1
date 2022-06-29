package ar.edu.unlam.tallerweb1.repositorios.reserve;

import ar.edu.unlam.tallerweb1.modelo.CreateReserveDto;
import ar.edu.unlam.tallerweb1.modelo.Reserve;

import java.util.List;

public interface IReserveRepository {


    Reserve makeReserve (Reserve reserve);

    List<Reserve> getReservesByUser(Long userId);

}
