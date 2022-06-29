package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.reserve.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReserveController {
    private final IReserveService reserveService;
    private final HttpSession session;

    @Autowired
    public ReserveController(IReserveService reserveService, HttpSession session) {
        this.reserveService = reserveService;
        this.session = session;
    }


    @RequestMapping("/reserves")
    public ModelAndView showAllReservesForUser() {
        User user = (User) session.getAttribute("user");

        if (user == null) return new ModelAndView("redirect:/login");

        ModelMap modelMap = new ModelMap();

        List<Reserve> reserves = reserveService.getReservesByUser(user.getId());

        modelMap.put("reserves", reserves);
        modelMap.put("userName", user.getName());


        return new ModelAndView("reserves", modelMap);
    }


}
