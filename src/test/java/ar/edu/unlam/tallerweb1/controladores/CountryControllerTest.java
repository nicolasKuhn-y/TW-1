package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.controladores.messages.CountryMessages;
import ar.edu.unlam.tallerweb1.exceptions.CountryNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.country.CountryService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CountryControllerTest {
    private CountryService countryService;
    private CountryController countryController;

    @Before
    public void init() {
        countryService = mock(CountryService.class);
        countryController = new CountryController(countryService);
    }

    @Test
    public void itShouldShowAllTheCountries() {
        whenThereAreCountries();

        List<Country> countries = (List<Country>) getCountriesViewModelWithoutParam().get("countries");

        Assertions.assertThat(countries).hasSize(2);
    }

    @Test
    public void theModelShouldHaveAllRequiredVaccines() {
        String countryCode = "AR";
        whenThereAreRequiredVaccines(countryCode);

        Set<Vaccine> vaccines = (Set<Vaccine>) getCountriesViewModelWithParam(countryCode).get("requiredVaccines");

        Assertions.assertThat(vaccines).hasSize(2);
    }

    @Test
    public void theModelShouldHaveAllRecommendedVaccines() {
        String countryCode = "AR";
        whenThereAreRecommendedVaccines(countryCode);

        Set<Vaccine> vaccines = (Set<Vaccine>) getCountriesViewModelWithParam(countryCode).get("recommendedVaccines");

        Assertions.assertThat(vaccines).hasSize(1);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoRequiredVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreNoRequiredVaccines(countryCode);

        String message = (String) getCountriesViewModelWithParam(countryCode).get("notFoundVaccinesRequired");

        Assertions.assertThat(message).isEqualTo(CountryMessages.NOT_FOUND_REQUIRED_VACCINES.message);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoRecommendedVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreNoRecommendedVaccines(countryCode);

        String message = (String) getCountriesViewModelWithParam(countryCode).get("notFoundVaccinesRecommended");

        Assertions.assertThat(message).isEqualTo(CountryMessages.NOT_FOUND_RECOMMENDED_VACCINES.message);
    }

    @Test
    public void itShouldReturnAErrorMessageIfNoCountryWasFound() {
        String countryCode = "BR";

        whenThereAreNoCountries(countryCode);

        String message = (String) getCountriesViewModelWithParam(countryCode).get("error");

        Assertions.assertThat(message).isEqualTo(CountryMessages.NOT_COUNTRY_FOUND.message);
    }

    private Map<String, Object> getCountriesViewModelWithParam(String countryCode) {
        ModelAndView mav = countryController.getCountryView(countryCode);

        return mav.getModel();
    }

    private Map<String, Object> getCountriesViewModelWithoutParam() {
        ModelAndView mav = countryController.getCountryView(null);

        return mav.getModel();
    }

    private void whenThereAreCountries() {
        when(countryService.getCountries()).thenReturn(List.of(new Country(), new Country()));
    }

    private void whenThereAreNoCountries(String code) {
        when(countryService.getVaccines(code)).thenThrow(
                new CountryNotFoundException(CountryMessages.NOT_COUNTRY_FOUND.message)
        );
    }

    private void whenThereAreRequiredVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine(), new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }

    private void whenThereAreNoRequiredVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", new HashSet<>(),
                "recommended", requiredVaccines
        ));
    }

    private void whenThereAreRecommendedVaccines(String countryName) {
        Set<Vaccine> recommendedVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "recommended", recommendedVaccines,
                "required", new HashSet<>()
        ));
    }

    private void whenThereAreNoRecommendedVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }
}
