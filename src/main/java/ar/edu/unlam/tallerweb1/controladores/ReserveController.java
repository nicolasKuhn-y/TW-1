package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.entities.LocalDateTimeConverter;
import ar.edu.unlam.tallerweb1.exceptions.HospitalWithoutAppointmentsException;
import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.reserve.dtos.CreateReserveDto;
import ar.edu.unlam.tallerweb1.servicios.reserve.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @RequestMapping(
            value = "/reserves-calendar",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CalendarReserve>> getReserves() {
        User user = (User) session.getAttribute("user");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<CalendarReserve> reserves = reserveService.getReservesForCalendar(user.getId());

        return new ResponseEntity<>(reserves, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/reserves/{hospitalId}", method = RequestMethod.POST)
    public ModelAndView createReserveForUser(
            @PathVariable("hospitalId") Long hospitalId,
            String date,
            String time,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            if (Objects.equals(date, "") || Objects.equals(time, ""))
                throw new ValidationException("La fecha y la hora no deben estar vacias");

            User user = (User) session.getAttribute("user");

            LocalDateTime dateTime = LocalDateTimeConverter.convertToLocalDateTime(date, time);

            CreateReserveDto reserveDto = new CreateReserveDto(dateTime, user, hospitalId);

            reserveService.makeReserve(reserveDto);

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        } catch (HospitalWithoutAppointmentsException | ValidationException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        }
    }

}
