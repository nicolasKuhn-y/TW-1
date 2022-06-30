package ar.edu.unlam.tallerweb1.servicios.vaccine;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    List<Vaccine> findVaccinesRecommendedByAge(LocalDate fechaNacimiento);

    List<Vaccine> findAllVaccine();
}