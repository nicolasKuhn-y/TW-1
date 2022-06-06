package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class CountryController {
    private final ICountryService countryService;

    @Autowired
    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/countries")
    public ModelAndView getCountryView() {
        ModelMap model = new ModelMap();
        List<Country> countries = countryService.getCountries();

        model.put("countries", countries);

        return new ModelAndView("country", model);
    }

    @RequestMapping(value = "/show-vaccines", method = RequestMethod.GET)
    public ModelAndView showRequiredVaccines(@RequestParam(value = "code") String countryName) {
        ModelMap model = new ModelMap();

        List<Country> countries = countryService.getCountries();
        Map<String, Set<Vaccine>> vaccines = countryService.getVaccines(countryName);

        model.put("countries", countries);

        if (vaccines.get("required").isEmpty()) {
            model.put("notFoundVaccinesRequired", "No hay vacunas requeridas para entrar al pais");
        }

        if (vaccines.get("recommended").isEmpty()) {
            model.put("notFoundVaccinesRecommended", "No hay vacunas recomendadas para entrar al pais");
        }

        model.put("requiredVaccines", vaccines.get("required"));
        model.put("recommendedVaccines", vaccines.get("recommended"));

        return new ModelAndView("country", model);
    }

}
