package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.HospitalWithoutAppointmentsException;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.servicios.reserve.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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

    @RequestMapping(value = "/reserves/{hospitalId}", method = RequestMethod.POST)
    public ModelAndView createReserveForUser(
            @PathVariable("hospitalId") Long hospitalId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime date,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            if (hospitalId == null || date == null)
                return new ModelAndView("redirect:/nearest-hospitals");

            User user = (User) session.getAttribute("user");

            CreateReserveDto reserveDto = new CreateReserveDto(date, user, hospitalId);

            reserveService.makeReserve(reserveDto);

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        } catch (HospitalWithoutAppointmentsException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        }
    }

}
