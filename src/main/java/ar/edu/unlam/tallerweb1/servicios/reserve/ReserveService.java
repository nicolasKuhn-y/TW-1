package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.exceptions.HospitalWithoutAppointmentsException;
import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.repositorios.hospital.IHospitalRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.IReserveRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReserveService implements IReserveService {
    private final IReserveRepository reserveRepository;
    private final IHospitalRepository hospitalRepository;
    private final ReserveMapper reserveMapper;

    @Autowired
    public ReserveService(
            IReserveRepository reserveRepository,
            IHospitalRepository hospitalRepository,
            ReserveMapper reserveMapper) {
        this.reserveRepository = reserveRepository;
        this.hospitalRepository = hospitalRepository;
        this.reserveMapper = reserveMapper;
    }

    @Override
    public List<Reserve> getReservesByUser(Long userId) {
        return reserveRepository.getReservesByUser(userId);
    }

    @Override
    public List<CalendarReserve> getReservesForCalendar(Long userId) {
        return reserveRepository.getReservesByUser(userId)
                .stream()
                .map(reserveMapper::mapReserveToCalendarReserve)
                .collect(Collectors.toList());
    }

    @Override
    public void makeReserve(CreateReserveDto reserveDto) {
        Hospital hospitalFound = hospitalRepository.getOneHospital(reserveDto.getHospitalId());

        if (hospitalFound.getAppointmentsAmount().equals(0))
            throw new HospitalWithoutAppointmentsException("No hay mas turnos por el dia de hoy.");

        hospitalFound.reduceAppointmentsAmount(1);

        reserveRepository.makeReserve(Reserve.create(reserveDto.getDateTime(), reserveDto.getUser(), hospitalFound));
    }
}
