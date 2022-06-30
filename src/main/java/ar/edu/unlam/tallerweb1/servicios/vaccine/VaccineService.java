package ar.edu.unlam.tallerweb1.servicios.vaccine;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.vaccine.IVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service @Transactional
public class VaccineService implements IVaccineService{


    private IVaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(IVaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public List<Vaccine> findVaccinesRecommendedByAge(int anio) {
        List<Vaccine> listVaccineRecommended = new LinkedList<>();
        Integer AgeUser = calcularEdad(anio);
        List<Vaccine> listAllVaccine = vaccineRepository.listAllVaccine();
        for (int i = 0; i < listAllVaccine.size(); i++) {
            int maxAge = listAllVaccine.get(i).getMaxAge();
            int minAge = listAllVaccine.get(i).getMinAge();
            if (AgeUser<maxAge && AgeUser>minAge){
                listVaccineRecommended.add(listAllVaccine.get(i));
            }
        }

        return listVaccineRecommended;
    }

    @Override
    public List<Vaccine> findAllVaccine() {
        return vaccineRepository.listAllVaccine();
    }

    private Integer calcularEdad(int anio) {
        LocalDate actual = LocalDate.now();

        return  actual.getYear()-anio;
    }

}