package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.appliedVaccine.AppliedVaccinesService;
import ar.edu.unlam.tallerweb1.servicios.appliedVaccine.IAppliedVaccinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppliedVaccinesController {
    private IAppliedVaccinesService appliedVaccinesService;

    @Autowired
    public AppliedVaccinesController(IAppliedVaccinesService appliedVaccinesService) {
        this.appliedVaccinesService=appliedVaccinesService;
    }


    public ModelAndView findVaccineApplied(User user) {
        ModelMap model = new ModelMap();
        List<Vaccine> appliedList = null;
        try {
            appliedList = appliedVaccinesService.findVaccineApplied(user);
            model.put("applied",appliedList);
        }catch (Exception e){
            model.put("error","No cuenta con vacunas aplicadas");
        }
        return new ModelAndView("appliedVaccine",model);
    }
}
