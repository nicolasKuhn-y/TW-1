package ar.edu.unlam.tallerweb1.repositorios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.util.List;

public interface ICountryRepository {
    List<Vaccine> getRequiredVaccines(String name);

    List<Country> getCountries();
}
