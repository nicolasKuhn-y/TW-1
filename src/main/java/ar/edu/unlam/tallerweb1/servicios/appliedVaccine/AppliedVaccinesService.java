package ar.edu.unlam.tallerweb1.servicios.appliedVaccine;

import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.appliedVacccine.AppliedVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class AppliedVaccinesService implements IAppliedVaccinesService {

    private AppliedVaccineRepository appliedVaccineRepository;

    @Autowired
    public AppliedVaccinesService(AppliedVaccineRepository appllieVaccineRepository) {
        this.appliedVaccineRepository=appllieVaccineRepository;
    }

    @Override
    public List findVaccineApplied(User user) {
        return null;
    }
}
