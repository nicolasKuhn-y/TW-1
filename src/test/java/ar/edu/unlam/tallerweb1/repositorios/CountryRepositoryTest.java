package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.country.CountryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CountryRepositoryTest extends SpringTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Transactional
    @Rollback
    public void itShouldReturnAllRequiredVaccinesGivenACountryName() {
        Country country = createCountry("PE");

        Vaccine vaccine1 = createVaccine("vaccineOne");
        Vaccine vaccine2 = createVaccine("vaccineTwo");


        country.setVaccinesRequired(List.of(vaccine1, vaccine2));

        this.session().save(country);

        List<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(country.getCode());

        Assertions.assertThat(requiredVaccines).hasSize(2);
    }

    @Test
    @Transactional
    @Rollback
    public void itShouldReturnAnEmptyListIfNoCountryWasFound() {
        List<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines("ZW");

        Assertions.assertThat(requiredVaccines).hasSize(0);
    }

    @Test
    @Transactional
    @Rollback
    public void itShouldReturnAllCountriesFound() {
        Country country1 = createCountry("PE");
        Country country2 = createCountry("AR");

        this.session().save(country1);
        this.session().save(country2);

        List<Country> countries = countryRepository.getCountries();

        Assertions.assertThat(countries).hasSize(2);
    }



    private Country createCountry(String code) {
        Country country = new Country();
        country.setCode(code);

        return country;
    }

    private Vaccine createVaccine(String name) {
        Vaccine vaccine = new Vaccine();

        vaccine.setName(name);

        return vaccine;
    }

}
