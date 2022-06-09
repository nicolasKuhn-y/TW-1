package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.controladores.messages.CountryMessages;
import ar.edu.unlam.tallerweb1.exceptions.CountryNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView getCountryView(@RequestParam(value = "code", required = false) String countryName) {
        try {
            ModelMap model = new ModelMap();

            if (countryName == null) {
                List<Country> countries = countryService.getCountries();
                model.put("countries", countries);
                return new ModelAndView("country", model);
            }

            List<Country> countries = countryService.getCountries();
            Map<String, Set<Vaccine>> vaccines = countryService.getVaccines(countryName);

            if (vaccines.get("required").isEmpty()) {
                model.put("notFoundVaccinesRequired", CountryMessages.NOT_FOUND_REQUIRED_VACCINES.message);
            }

            if (vaccines.get("recommended").isEmpty()) {
                model.put("notFoundVaccinesRecommended", CountryMessages.NOT_FOUND_RECOMMENDED_VACCINES.message);
            }

            model.put("countries", countries);
            model.put("requiredVaccines", vaccines.get("required"));
            model.put("recommendedVaccines", vaccines.get("recommended"));

            return new ModelAndView("country", model);
        } catch (CountryNotFoundException exception) {
            ModelMap model = new ModelMap();
            List<Country> countries = countryService.getCountries();

            model.put("countries", countries);
            model.put("error", exception.getMessage());

            return new ModelAndView("country", model);
        }
    }
}
