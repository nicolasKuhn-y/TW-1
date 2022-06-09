package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.servicios.hospital.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HospitalController {
    private final IHospitalService hospitalService;

    @Autowired
    public HospitalController(IHospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @RequestMapping(value = "/hospitals", method = RequestMethod.POST)
    public ModelAndView getNearestHospitals(
            @RequestParam("lat") Double lat,
            @RequestParam("long") Double lng
    ) {
        try {
            ModelMap model = new ModelMap();

            List<Hospital> hospitalList = hospitalService.getNearestHospitalsByLocation(lat, lng);

            model.put("hospitals", hospitalList);

            return null;
        } catch (InvalidCoordinatesException exception) {
            ModelMap model = new ModelMap("error", exception.getMessage());

            return null;
        }
    }

}
