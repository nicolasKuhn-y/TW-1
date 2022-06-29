package ar.edu.unlam.tallerweb1.servicios.reserve;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.repositorios.reserve.IReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReserveService implements IReserveService {
    private final IReserveRepository reserveRepository;

    @Autowired
    public ReserveService(IReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public List<Reserve> getReservesByUser(Long userId) {
        if (userId == null) throw new UserNotFoundException("UserId was not present in method getReservesByUser");

        return reserveRepository.getReservesByUser(userId);
    }
}
