package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.country.CountryRepository;
import ar.edu.unlam.tallerweb1.servicios.country.CountryService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void itShouldReturnAllTheVaccinesRequiredForACountry() {
        String countryCode = "PE";

        whenThereAreVaccines(countryCode);

        List<Vaccine> requiredVaccines = countryService.getVaccines(countryCode);

        Assertions.assertThat(requiredVaccines).hasSize(1);
    }

    @Test
    public void itShouldReturnAllTheCountries() {
        whenThereAreCountries();

        List<Country> countries = countryService.getCountries();

        Assertions.assertThat(countries).hasSize(2);
    }

    private void whenThereAreVaccines(String countryCode) {
        when(countryRepository.getRequiredVaccines(countryCode)).thenReturn(List.of(new Vaccine()));
    }

    private void whenThereAreCountries() {
        Country country1 = new Country();
        Country country2 = new Country();

        when(countryRepository.getCountries()).thenReturn(List.of(country1, country2));
    }

}
