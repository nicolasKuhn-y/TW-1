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

        CountryVaccineGroup relation1 = createCountryVaccine(country, "vaccineOne", Boolean.TRUE);
        CountryVaccineGroup relation2 = createCountryVaccine(country, "vaccineTwo", Boolean.TRUE);

        country.setVaccineGroups(Set.of(relation1, relation2));

        this.session().save(country);

        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(country.getCode());

        Assertions.assertThat(requiredVaccines).hasSize(2);
    }

    @Test
    public void itShouldReturnOnlyTheRequiredVaccines() {
        Country country = createCountry("PE");

        CountryVaccineGroup relation1 = createCountryVaccine(country, "vaccineOne", Boolean.TRUE);
        CountryVaccineGroup relation2 = createCountryVaccine(country, "vaccineTwo", Boolean.FALSE);

        country.setVaccineGroups(Set.of(relation1, relation2));

        this.session().save(country);

        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines(country.getCode());

        Assertions.assertThat(requiredVaccines).hasSize(1);
    }

    @Test
    public void itShouldReturnAEmptySetIfNoRequiredVaccineWasFound() {
        Set<Vaccine> requiredVaccines = countryRepository.getRequiredVaccines("ZW");

        Assertions.assertThat(requiredVaccines).hasSize(0);
    }

    @Test
    public void itShouldReturnAEmptySetIfNoRecommendedVaccineWasFound() {
        Set<Vaccine> requiredVaccines = countryRepository.getRecommendedVaccines("ZW");

        Assertions.assertThat(requiredVaccines).hasSize(0);
    }

    @Test
    public void itShouldReturnAllRecommendedVaccines() {
        Country country = createCountry("PE");

        CountryVaccineGroup relation1 = createCountryVaccine(country, "vaccineOne", Boolean.FALSE);
        CountryVaccineGroup relation2 = createCountryVaccine(country, "vaccineTwo", Boolean.FALSE);

        country.setVaccineGroups(Set.of(relation1, relation2));

        this.session().save(country);

        Set<Vaccine> recommendedVaccines = countryRepository.getRecommendedVaccines(country.getCode());

        Assertions.assertThat(recommendedVaccines).hasSize(2);
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

    @Test
    public void itShouldReturnACountryByCode() {
        String code = "AR";
        Country country = createCountry("AR");

        this.session().save(country);

        Country countryFound = countryRepository.getCountryByCode(code);

        Assertions.assertThat(countryFound.getCode()).isEqualTo(code);
    }

    private CountryVaccineGroup createCountryVaccine(Country country, String vaccineName, Boolean isRequired) {
        Vaccine vaccine = createVaccine(vaccineName);

        CountryVaccineGroup vaccineGroup = new CountryVaccineGroup(vaccine, country, isRequired);

        this.session().save(vaccineGroup);

        return vaccineGroup;
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
