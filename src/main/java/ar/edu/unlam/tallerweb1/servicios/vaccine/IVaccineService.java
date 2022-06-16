package ar.edu.unlam.tallerweb1.servicios.vaccine;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVaccineService {

 List<Vaccine> findVaccinesRecommendedByAge(Date fechaNacimiento);

 List<Vaccine> findAllVaccine();
}
