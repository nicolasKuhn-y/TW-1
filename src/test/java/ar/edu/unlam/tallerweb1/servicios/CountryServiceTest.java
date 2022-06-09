package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.CountryNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.country.CountryRepository;
import ar.edu.unlam.tallerweb1.servicios.country.CountryService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

public class CountryServiceTest {
    private CountryRepository countryRepository;
    private CountryService countryService;

    @Before
    public void init() {
        countryRepository = mock(CountryRepository.class);
        countryService = new CountryService(countryRepository);
    }

    @Test
    public void itShouldReturnAllTheVaccinesOfACountry() {
        String countryCode = "PE";

        whenThereAreVaccines(countryCode);

        Map<String, Set<Vaccine>> vaccines = countryService.getVaccines(countryCode);

        Assertions.assertThat(vaccines.get("required")).hasSize(1);
        Assertions.assertThat(vaccines.get("recommended")).hasSize(2);
    }

    @Test
    public void itShouldReturnAllTheCountries() {
        whenThereAreCountries();

        List<Country> countries = countryService.getCountries();

        Assertions.assertThat(countries).hasSize(2);
    }

    @Test(expected = CountryNotFoundException.class)
    public void itShouldThrowAnErrorWhenNoCountryWasFound() {
        String code = "AR";
        whenThereIsNoCountryWithCode(code);

        countryService.getVaccines(code);

        verifyNoRepositoryMethodWasCalled(code);
    }

    private void whenThereAreVaccines(String countryCode) {
        when(countryRepository.getCountryByCode(countryCode)).thenReturn(new Country());
        when(countryRepository.getRequiredVaccines(countryCode)).thenReturn(Set.of(new Vaccine()));
        when(countryRepository.getRecommendedVaccines(countryCode)).thenReturn(Set.of(new Vaccine(), new Vaccine()));
    }

    private void whenThereAreCountries() {
        Country country1 = new Country();
        Country country2 = new Country();

        when(countryRepository.getCountries()).thenReturn(List.of(country1, country2));
    }

    private void whenThereIsNoCountryWithCode(String code) {
        when(countryRepository.getCountryByCode(code)).thenReturn(null);
    }

    private void verifyNoRepositoryMethodWasCalled(String code) {
        verify(countryRepository,never()).getRecommendedVaccines(code);
        verify(countryRepository,never()).getRequiredVaccines(code);
    }

}
