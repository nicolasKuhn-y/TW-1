package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.controladores.messages.VaccineRecommenderMessages;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.vaccineRecommender.VaccineRecommenderService;
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


public class VaccineRecommenderControllerTest {
    private VaccineRecommenderService vaccineRecommenderService;
    private VaccineRecommenderController vaccineRecommenderController;

    @Before
    public void init() {
        vaccineRecommenderService = mock(VaccineRecommenderService.class);
        vaccineRecommenderController = new VaccineRecommenderController(vaccineRecommenderService);
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

        Assertions.assertThat(message).isEqualTo(VaccineRecommenderMessages.NOT_FOUND_REQUIRED_VACCINES.message);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoRecommendedVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreNoRecommendedVaccines(countryCode);

        String message = (String) getCountriesViewModelWithParam(countryCode).get("notFoundVaccinesRecommended");

        Assertions.assertThat(message).isEqualTo(VaccineRecommenderMessages.NOT_FOUND_RECOMMENDED_VACCINES.message);
    }

    private Map<String, Object> getCountriesViewModelWithParam(String code) {
        ModelAndView mav = vaccineRecommenderController.getCountryView(code);

        return mav.getModel();
    }

    private Map<String, Object> getCountriesViewModelWithoutParam() {
        ModelAndView mav = vaccineRecommenderController.getCountryView(null);

        return mav.getModel();
    }

    private void whenThereAreRequiredVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine(), new Vaccine());

        when(vaccineRecommenderService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }

    private void whenThereAreNoRequiredVaccines(String countryName) {
        Set<Vaccine> recommendedVaccines = Set.of(new Vaccine());

        when(vaccineRecommenderService.getVaccines(countryName)).thenReturn(Map.of(
                "required", new HashSet<>(),
                "recommended", recommendedVaccines
        ));
    }

    private void whenThereAreRecommendedVaccines(String countryName) {
        Set<Vaccine> recommendedVaccines = Set.of(new Vaccine());

        when(vaccineRecommenderService.getVaccines(countryName)).thenReturn(Map.of(
                "recommended", recommendedVaccines,
                "required", new HashSet<>()
        ));
    }

    private void whenThereAreNoRecommendedVaccines(String countryName) {
        Set<Vaccine> requiredVaccines = Set.of(new Vaccine());

        when(vaccineRecommenderService.getVaccines(countryName)).thenReturn(Map.of(
                "required", requiredVaccines,
                "recommended", new HashSet<>()
        ));
    }

    private void whenThereAreCountries() {
        List<Country> countries = List.of(new Country(), new Country());

        when(vaccineRecommenderService.getCountries()).thenReturn(countries);
    }
}
