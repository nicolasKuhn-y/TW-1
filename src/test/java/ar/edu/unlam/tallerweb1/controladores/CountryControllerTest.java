package ar.edu.unlam.tallerweb1.controladores;

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
    private final String NOT_FOUND_REQUIRED_VACCINES = "No hay vacunas requeridas para entrar al pais";
    private final String NOT_FOUND_RECOMMENDED_VACCINES = "No hay vacunas recomendadas para entrar al pais";

    @Before
    public void init() {
        countryService = mock(CountryService.class);
        countryController = new CountryController(countryService);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoRequiredVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreRecommended(countryCode);

        String message = (String) getShowVaccinesModel(countryCode).get("notFoundVaccinesRequired");

        Assertions.assertThat(message).isEqualTo(NOT_FOUND_REQUIRED_VACCINES);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoRecommendedVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreNoRecommendedVaccines(countryCode);

        String message = (String) getShowVaccinesModel(countryCode).get("notFoundVaccinesRecommended");

        Assertions.assertThat(message).isEqualTo(NOT_FOUND_RECOMMENDED_VACCINES);
    }

    @Test
    public void itShouldShowAllTheCountries() {
        whenThereAreCountries();

        List<Country> countries = (List<Country>) getCountriesViewModel().get("countries");

        Assertions.assertThat(countries).hasSize(2);
    }

    @Test
    public void theModelShouldHaveAllRequiredVaccines() {
        String countryCode = "AR";
        whenThereAreRequiredVaccines(countryCode);

        Set<Vaccine> vaccines = (Set<Vaccine>) getShowVaccinesModel(countryCode).get("requiredVaccines");

        Assertions.assertThat(vaccines).hasSize(2);
    }

    @Test
    public void theModelShouldHaveAllRecommendedVaccines() {
        String countryCode = "AR";
        whenThereAreRecommendedVaccines(countryCode);

        Set<Vaccine> vaccines = (Set<Vaccine>) getShowVaccinesModel(countryCode).get("recommendedVaccines");

        Assertions.assertThat(vaccines).hasSize(1);
    }


    private Map<String, Object> getShowVaccinesModel(String countryName) {
        ModelAndView mav = countryController.showRequiredVaccines(countryName);

        return mav.getModel();
    }

    private Map<String, Object> getCountriesViewModel() {
        ModelAndView mav = countryController.getCountryView();

        return mav.getModel();
    }

    private void whenThereAreRequiredVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine(), new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }

    private void whenThereAreRecommended(String countryName) {
        Set<Vaccine> recommendedVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", new HashSet<>(),
                "recommended", recommendedVaccines
        ));
    }

    private void whenThereAreNoRecommendedVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }

    private void whenThereAreRecommendedVaccines(String countryName) {
        Set<Vaccine> recommendedVaccines = Set.of(new Vaccine());

        when(countryService.getVaccines(countryName)).thenReturn(Map.of(
                "recommended", recommendedVaccines,
                "required", new HashSet<>()
        ));
    }

    private void whenThereAreCountries() {
        when(countryService.getCountries()).thenReturn(List.of(new Country(), new Country()));
    }
}
