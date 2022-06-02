package ar.edu.unlam.tallerweb1.servicios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.util.List;

public interface ICountryService {

    List<Vaccine> getVaccines(String countryName);

    List<Country> getCountries();
}
