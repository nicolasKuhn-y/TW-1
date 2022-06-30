package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.ReserveRepository;
import ar.edu.unlam.tallerweb1.servicios.reserve.ReserveService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReserveServiceTest {
    private ReserveRepository reserveRepository;
    private HospitalRepository hospitalRepository;
    private ReserveService reserveService;

    @Before
    public void init() {
        reserveRepository = mock(ReserveRepository.class);
        hospitalRepository = mock(HospitalRepository.class);
        reserveService = new ReserveService(reserveRepository, hospitalRepository);
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

    @Test
    public void itShouldMakeAReserveWithGivenData() {
        CreateReserveDto reserveDto = new CreateReserveDto(LocalDateTime.now(), new User(), 1L);
        whenHospitalWithIdWasFound(reserveDto.getHospitalId());

        reserveService.makeReserve(reserveDto);

        Reserve reserve = Reserve.create(reserveDto.getDateTime(), reserveDto.getUser(), new Hospital());
        verifyThatReserveRepsitorySaveReserve(reserve);
    }

    private void whenThereAreReservesForUser(Long id) {
        when(reserveRepository.getReservesByUser(id)).thenReturn(List.of(new Reserve(), new Reserve()));
    }

    private void whenHospitalWithIdWasFound(Long id) {
        when(hospitalRepository.getOneHospital(id)).thenReturn(new Hospital());
    }

    private void verifyThatReserveRepsitorySaveReserve(Reserve reserve) {
        verify(reserveRepository, times(1)).makeReserve(reserve);
    }
}
