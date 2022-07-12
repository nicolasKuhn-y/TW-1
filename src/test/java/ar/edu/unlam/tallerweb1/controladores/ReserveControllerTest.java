package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.reserve.ReserveService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveControllerTest {
    private ReserveService reserveService;
    private ReserveController reserveController;
    private HttpSession session;

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
        LocalDateTime date = LocalDateTime.now();

        whenUserIsLogged(userId);

        ModelAndView mav = reserveController.createReserveForUser(hospitalId, date, null);

        String wantedViewName = "redirect:/hospitals/" + 1;

        Assertions.assertThat(mav.getViewName()).isEqualTo(wantedViewName);
    }

    @Test
    public void itShouldRedirectToHospitalsViewIfHospitalIdIsNull() {
        LocalDateTime date = LocalDateTime.now();

        ModelAndView mav = reserveController.createReserveForUser(null, date, null);

        String wantedViewName = "redirect:/nearest-hospitals";

        Assertions.assertThat(mav.getViewName()).isEqualTo(wantedViewName);
    }

    @Test
    public void itShouldRedirectToHospitalsViewIfHospitalDateParameterIsNull() {
        Long hospitalId = 1L;

        ModelAndView mav = reserveController.createReserveForUser(hospitalId, null, null);

        String wantedViewName = "redirect:/nearest-hospitals";

        Assertions.assertThat(mav.getViewName()).isEqualTo(wantedViewName);
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

    private void whenUserIsNotLogged() {
        when(session.getAttribute("user")).thenReturn(null);
    }

    private void whenThereAreReserves(Long id) {
        when(reserveService.getReservesByUser(id))
                .thenReturn(List.of(new Reserve(), new Reserve()));
    }
}
