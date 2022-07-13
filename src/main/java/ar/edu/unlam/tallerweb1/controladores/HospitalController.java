package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.HospitalNotFoundException;
import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.servicios.hospital.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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


    @RequestMapping(value = "/nearest-hospitals", method = RequestMethod.GET)
    public ModelAndView getNearestHospitals(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "long", required = false) Double lng,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        try {
            ModelMap model = new ModelMap();

            if (lat == null || lng == null) {
                return new ModelAndView("nearestHospitals", model);
            }

            List<Hospital> hospitalList = hospitalService.getNearestHospitalsByLocation(lat, lng, limit);

            model.put("hospitals", hospitalList);

            return new ModelAndView("nearestHospitals", model);
        } catch (InvalidCoordinatesException exception) {
            ModelMap model = new ModelMap("error", exception.getMessage());

            return new ModelAndView("nearestHospitals", model);
        }
    }

    @RequestMapping("/hospitals/{idHospital}")
    public ModelAndView getHospitalDetail(@PathVariable("idHospital") Long id) {
        try {
            ModelMap model = new ModelMap();

            Hospital hospital = hospitalService.getHospitalById(id);

            model.put("hospital", hospital);

            return new ModelAndView("hospitalDetail", model);
        } catch (HospitalNotFoundException exception) {


            return new ModelAndView();
        }
    }
}
