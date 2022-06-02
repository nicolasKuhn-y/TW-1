package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.country.CountryService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryControllerTest {
    private CountryService countryService;
    private CountryController countryController;

    private final String NOT_FOUND_MESSAGE = "No hay vacunas requeridas para entrar al pais";

    @Before
    public void init() {
        countryService = mock(CountryService.class);
        countryController = new CountryController(countryService);
    }

    @Test
    public void itShouldShowAMessageIfThereIsNoVaccinesForTheCountry() {
        String countryCode = "BR";
        whenThereAreNoVaccines(countryCode);

        String message = (String) getShowVaccinesModel(countryCode).get("notFoundVaccines");

        Assertions.assertThat(message).isEqualTo(NOT_FOUND_MESSAGE);
    }

    @Test
    public void itShouldShowAllTheRequiredVaccines() {
        String countryCode = "AR";
        whenThereAreVaccines(countryCode);

        List<Vaccine> vaccines = (List<Vaccine>) getShowVaccinesModel(countryCode).get("vaccines");

        Assertions.assertThat(vaccines).hasSize(1);
    }


    private Map<String, Object> getShowVaccinesModel(String countryName) {
        ModelAndView mav = countryController.showRequiredVaccines(countryName);

        return mav.getModel();
    }

    private void whenThereAreVaccines(String countryName) {
        when(countryService.getVaccines(countryName)).thenReturn(List.of(new Vaccine()));
    }

    private void whenThereAreNoVaccines(String countryName) {
        when(countryService.getVaccines(countryName)).thenReturn(new ArrayList<>());
    }


}
