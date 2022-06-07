package ar.edu.unlam.tallerweb1.servicios.vaccineRecommender;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IVaccineRecommenderService {

    Map<String,Set<Vaccine>> getVaccines(String countryCode);

    List<Country> getCountries();
}
