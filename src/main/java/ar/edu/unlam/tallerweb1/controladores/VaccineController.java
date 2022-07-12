package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.vaccine.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class VaccineController {

    private IVaccineService vaccineService;
    @Autowired
    public VaccineController(IVaccineService vaccineService){
        this.vaccineService=vaccineService;
    }

    @RequestMapping(value="/vaccine-recommended-by-age",method = RequestMethod.GET)
    public ModelAndView listVaccineRecommendedByAge(@RequestParam(value = "anio", required = false) Integer anio){
        ModelMap model = new ModelMap();
        try {
            List<Vaccine> listRecommended = vaccineService.findVaccinesRecommendedByAge(anio);
            model.put("vaccine",listRecommended);
        }catch (Exception e){
            model.put("error","No se recomiendan vacunas para su edad");
        }
        return new ModelAndView("/vaccine-recommended-by-age",model);
    }

    @RequestMapping("/all-vaccine")
    public ModelAndView listAllVaccine(){
        ModelMap model = new ModelMap();
        try{
            List<Vaccine>  listVaccine = vaccineService.findAllVaccine();
            model.put("vaccine",listVaccine);
        }catch (Exception e){
            model.put("error","No se encontraron vacunas en la base de datos");
        }
        return new ModelAndView("/all-vaccine",model);
    }
}
