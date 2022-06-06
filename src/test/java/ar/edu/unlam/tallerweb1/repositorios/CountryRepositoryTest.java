package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.CountryVaccineGroup;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.country.CountryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Rollback
public class CountryRepositoryTest extends SpringTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void itShouldReturnAllRequiredVaccinesGivenACountryName() {
        Country country = createCountry("PE");

        Vaccine vaccine1 = createVaccine("vaccineOne");
        Vaccine vaccine2 = createVaccine("vaccineTwo");

        CountryVaccineGroup relation1 = new CountryVaccineGroup(vaccine1, country, Boolean.TRUE);
        CountryVaccineGroup relation2 = new CountryVaccineGroup(vaccine2, country, Boolean.TRUE);

        country.setVaccineGroups(Set.of(relation1, relation2));

        this.session().save(relation1);
        this.session().save(relation2);
        this.session().save(country);

        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(country.getCode());

        Assertions.assertThat(requiredVaccines).hasSize(2);
    }

    @Test
    public void itShouldReturnOnlyTheRequiredVaccines() {
        Country country = createCountry("PE");

        Vaccine vaccine1 = createVaccine("vaccineOne");
        Vaccine vaccine2 = createVaccine("vaccineTwo");

        CountryVaccineGroup relation1 = new CountryVaccineGroup(vaccine1, country, Boolean.TRUE);
        CountryVaccineGroup relation2 = new CountryVaccineGroup(vaccine2, country, Boolean.FALSE);

        country.setVaccineGroups(Set.of(relation1, relation2));

        this.session().save(relation1);
        this.session().save(relation2);
        this.session().save(country);

        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(country.getCode());

        Assertions.assertThat(requiredVaccines).hasSize(1);
    }

    @Test
    public void itShouldReturnNullIfNoCountryWasFound() {
        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines("ZW");

        Assertions.assertThat(requiredVaccines).isNull();
    }

    @Test
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
