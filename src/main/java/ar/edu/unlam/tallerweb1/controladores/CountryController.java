package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.CountryVaccineGroup;
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
        Set<Vaccine> requiredVaccines = countryService.getVaccines(countryName);

        model.put("countries", countries);

        if (requiredVaccines.isEmpty()) {
            model.put("notFoundVaccines", "No hay vacunas requeridas para entrar al pais");
            return new ModelAndView("country", model);
        }

        model.put("vaccines", requiredVaccines);

        return new ModelAndView("country", model);
    }

}
