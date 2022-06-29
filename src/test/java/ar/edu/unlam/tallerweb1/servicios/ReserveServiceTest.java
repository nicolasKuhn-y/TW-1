package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.repositorios.reserve.ReserveRepository;
import ar.edu.unlam.tallerweb1.servicios.reserve.ReserveService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveServiceTest {
    private ReserveRepository reserveRepository;
    private ReserveService reserveService;


    @Before
    public void init() {
        reserveRepository = mock(ReserveRepository.class);

        reserveService = new ReserveService(reserveRepository);

    }

    @Test
    public void itShouldReturnAllReservesGivenUserId() {
        Long userId = 1L;

        whenThereAreReservesForUser(userId);

        List<Reserve> userReserves = reserveService.getReservesByUser(userId);

        Assertions.assertThat(userReserves).hasSize(2);
    }

    @Test(expected = UserNotFoundException.class)
    public void itShouldThrowUserNotFoundExceptionIfIdIsNotProvided() {
        Long userId = null;

        reserveService.getReservesByUser(userId);
    }

    private void whenThereAreReservesForUser(Long id) {
        when(reserveRepository.getReservesByUser(id)).thenReturn(List.of(new Reserve(), new Reserve()));
    }


}
