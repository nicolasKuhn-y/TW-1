package ar.edu.unlam.tallerweb1.repositorios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;

import java.util.List;
import java.util.Set;

public interface ICountryRepository {
    Set<Vaccine> getRequiredVaccines(String code);

    Set<Vaccine> getRecommendedVaccines(String code);

    List<Country> getCountries();

    Country getCountryByCode(String code);
}
