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
import java.util.List;

@Controller
public class VaccineController {

    private IVaccineService vaccineService;
    @Autowired
    public VaccineController(IVaccineService vaccineService){
        this.vaccineService=vaccineService;
    }

    @RequestMapping(value="contraSegura123",method = RequestMethod.POST)
    public ModelAndView listVaccineRecommendedByAge(@RequestParam(value = "fechaNac", required = true) LocalDate fechaNac){
        ModelMap model = new ModelMap();
        List<Vaccine> listRecommended = null;
        try {
            listRecommended = vaccineService.findVaccinesRecommendedByAge(fechaNac);
            model.put("vaccine",listRecommended);
        }catch (Exception e){
            model.put("error","No se recomiendan vacunas para su edad");
        }
        return new ModelAndView("/recommended-vaccine",model);
    }

    @RequestMapping("/all-vaccine")
    public ModelAndView listAllVaccine(){
        ModelMap model = new ModelMap();
        List<Vaccine> listVaccine = null;
        try{
            listVaccine = vaccineService.findAllVaccine();
            model.put("vaccine",listVaccine);
        }catch (Exception e){
            model.put("error","No se encontraron vacunas en la base de datos");
        }
        return new ModelAndView("/all-vaccine",model);
    }
}
