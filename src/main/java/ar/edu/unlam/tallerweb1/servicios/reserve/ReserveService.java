package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.repositorios.hospital.IHospitalRepository;
import ar.edu.unlam.tallerweb1.repositorios.reserve.IReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReserveService implements IReserveService {
    private final IReserveRepository reserveRepository;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public ReserveService(
            IReserveRepository reserveRepository,
            IHospitalRepository hospitalRepository
    ) {
        this.reserveRepository = reserveRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Reserve> getReservesByUser(Long userId) {
        if (userId == null) throw new UserNotFoundException("UserId was not present in method getReservesByUser");

        return reserveRepository.getReservesByUser(userId);
    }

    @Override
    public void makeReserve(CreateReserveDto reserveDto) {
        Hospital hospitalFound = hospitalRepository.getOneHospital(reserveDto.getHospitalId());

        reserveRepository.makeReserve(Reserve.create(reserveDto.getDateTime(), reserveDto.getUser(), hospitalFound));
    }
}
