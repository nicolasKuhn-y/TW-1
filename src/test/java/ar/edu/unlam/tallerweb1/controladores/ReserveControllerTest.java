package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.CalendarReserve;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.reserve.ReserveService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveControllerTest {
    private ReserveService reserveService;
    private ReserveController reserveController;
    private HttpSession session;
    private RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

    @Before
    public void init() {
        reserveService = mock(ReserveService.class);
        session = mock(HttpSession.class);
        reserveController = new ReserveController(reserveService, session);
    }

    @Test
    public void itShouldReturnAllReservesFromTheUser() {
        Long userId = 1L;

        whenUserIsLogged(userId);
        whenThereAreReserves(userId);

        List<Reserve> reserves = (List<Reserve>) getAllReservesModel().get("reserves");

        Assertions.assertThat(reserves).hasSize(2);
    }

    @Test
    public void itShouldRedirectToLoginIfUserIsNotFound() {
        whenUserIsNotLogged();

        ModelAndView mav = reserveController.showAllReservesForUser();

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void itShouldReturnAKeyWithTheUserName() {
        Long userId = 1L;
        whenUserIsLogged(userId);

        String message = (String) getAllReservesModel().get("userName");

        Assertions.assertThat(message).isEqualTo("Carlos");
    }

    @Test
    public void itShouldRedirectToHospitalDetailWhenReserveWasMade() {
        Long hospitalId = 1L;
        Long userId = 1L;
        String date = "2000-01-01";
        String time = "03:07";
        whenUserIsLogged(userId);

        ModelAndView mav = reserveController.createReserveForUser(hospitalId, date, time, redirectAttributes);

        String wantedViewName = "redirect:/hospitals/" + 1;

        Assertions.assertThat(mav.getViewName()).isEqualTo(wantedViewName);
    }

    @Test
    public void itShouldRedirectToHospitalDetailWithErrorMessageIfDatesAreNotSend() {
        Long hospitalId = 1L;
        Long userId = 1L;
        String date = "";
        String time = "";
        whenUserIsLogged(userId);

        ModelAndView mav = reserveController.createReserveForUser(hospitalId, date, time, redirectAttributes);

        String wantedViewName ="redirect:/hospitals/" + hospitalId;

        Assertions.assertThat(mav.getViewName()).isEqualTo(wantedViewName);
    }

    @Test
    public void itShouldReturnAResponseWithFormattedReservesForCalendar() {
        Long userId = 1L;
        whenUserIsLogged(userId);

        whenThereAreCalendarReserves(userId);

        var response = reserveController.getReserves();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).hasSize(1);
    }

    private Map<String, Object> getAllReservesModel() {
        ModelAndView mav = reserveController.showAllReservesForUser();

        return mav.getModel();
    }

    private void whenUserIsLogged(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Carlos");

        when(session.getAttribute("user")).thenReturn(user);
    }

    private void whenThereAreCalendarReserves(Long id) {
        when(reserveService.getReservesForCalendar(id)).thenReturn(List.of(
                new CalendarReserve("Belgrano", "2022-01-01 11:00:00"))
        );
    }

    private void whenUserIsNotLogged() {
        when(session.getAttribute("user")).thenReturn(null);
    }

    private void whenThereAreReserves(Long id) {
        when(reserveService.getReservesByUser(id))
                .thenReturn(List.of(new Reserve(), new Reserve()));
    }
}
