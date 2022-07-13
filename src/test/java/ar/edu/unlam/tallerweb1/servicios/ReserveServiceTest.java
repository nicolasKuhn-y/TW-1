package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.HospitalWithoutAppointmentsException;
import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.ReserveRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.servicios.reserve.ReserveMapper;
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
    private ReserveMapper reserveMapper;
    private ReserveService reserveService;

    @Before
    public void init() {
        reserveRepository = mock(ReserveRepository.class);
        hospitalRepository = mock(HospitalRepository.class);
        reserveMapper = mock(ReserveMapper.class);
        reserveService = new ReserveService(reserveRepository, hospitalRepository, reserveMapper);
    }

    @Test
    public void itShouldReturnAllReservesGivenUserId() {
        Long userId = 1L;

        whenThereAreReservesForUser(userId);

        List<Reserve> userReserves = reserveService.getReservesByUser(userId);

        Assertions.assertThat(userReserves).hasSize(2);
    }

    @Test
    public void itShouldMakeAReserveWithGivenData() {
        CreateReserveDto reserveDto = new CreateReserveDto(LocalDateTime.now(), new User(), 1L);
        whenHospitalWithIdWasFound(reserveDto.getHospitalId());

        reserveService.makeReserve(reserveDto);

        Reserve reserve = Reserve.create(reserveDto.getDateTime(), reserveDto.getUser(), new Hospital());
        verifyThatReserveRepositorySaveReserve(reserve);
    }

    @Test(expected = HospitalWithoutAppointmentsException.class)
    public void itShouldThrowAnExceptionIfHospitalDoestHaveMoreAppointmentsLeft() {
        CreateReserveDto reserveDto = new CreateReserveDto(LocalDateTime.now(), new User(), 1L);

        whenHospitalWithoutAppointmentsWasFound(reserveDto.getHospitalId());

        reserveService.makeReserve(reserveDto);
    }

    @Test
    public void itShouldReduceAppointmentAvailable() {
        CreateReserveDto reserveDto = new CreateReserveDto(LocalDateTime.now(), new User(), 1L);

        Hospital hospital = whenHospitalWithIdWasFound(1L);
        reserveService.makeReserve(reserveDto);

        Assertions.assertThat(hospital.getAppointmentsAmount()).isEqualTo(9);
    }

    @Test
    public void itShouldReturnACalendarReserveList() {
        Long userId = 1L;
        whenThereAreReservesForUser(userId);

        List<CalendarReserve> reserves = reserveService.getReservesForCalendar(userId);

        Assertions.assertThat(reserves).hasSize(2);
    }

    private void whenThereAreReservesForUser(Long id) {
        when(reserveRepository.getReservesByUser(id)).thenReturn(List.of(new Reserve(), new Reserve()));
    }

    private Hospital whenHospitalWithIdWasFound(Long id) {
        Hospital hospital = new Hospital();
        hospital.setAppointmentsAmount(10);

        when(hospitalRepository.getOneHospital(id)).thenReturn(hospital);

        return hospital;
    }

    private void whenHospitalWithoutAppointmentsWasFound(Long id) {
        Hospital hospital = new Hospital();
        hospital.setAppointmentsAmount(0);

        when(hospitalRepository.getOneHospital(id)).thenReturn(hospital);
    }

    private void verifyThatReserveRepositorySaveReserve(Reserve reserve) {
        verify(reserveRepository, times(1)).makeReserve(reserve);
    }
}
