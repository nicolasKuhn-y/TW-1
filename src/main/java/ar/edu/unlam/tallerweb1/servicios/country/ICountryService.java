package ar.edu.unlam.tallerweb1.servicios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICountryService {

    Map<String,Set<Vaccine>> getVaccines(String countryCode);

    List<Country> getCountries();
}
