package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.HospitalNotFoundException;
import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;
import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.servicios.comment.CommentService;
import ar.edu.unlam.tallerweb1.servicios.hospital.HospitalService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HospitalControllerTest {
    private HospitalService hospitalService;
    private CommentService commentService;
    private HospitalController hospitalController;

    private final String ERROR_MESSAGE = "Alguno de los valores lat o long es incorrecto.";

    @Before
    public void init() {
        hospitalService = mock(HospitalService.class);
        commentService = mock(CommentService.class);
        hospitalController = new HospitalController(hospitalService, commentService);
    }

    @Test
    public void theModelShouldHaveAllTheHospitalsSorted() {
        Double lat = -34.665663289716925;
        Double lgn = -58.60524767319679;
        Integer limit = 2;

        whenHospitalsAreReturned(lat, lgn, limit);

        List<Hospital> hospitals = (List<Hospital>) getHospitalsModel(lat, lgn, limit).get("hospitals");

        Assertions.assertThat(hospitals).hasSize(3);
    }

    @Test
    public void itShouldShowAMessageIfLatOrLongAreNotValid() {
        Double lat = -4544.665663289716925;
        Double lgn = -5558.60524767319679;
        Integer limit = 2;

        whenAnErrorIsThrow(lat, lgn, limit);

        String errorMessage = (String) getHospitalsModel(lat, lgn, limit).get("error");

        Assertions.assertThat(errorMessage).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    public void itShouldRedirectToHomeIfLatOrLongAreNotSend() {
        Double lat = -4544.665663289716925;
        Double lgn = null;

        var mav = hospitalController.getNearestHospitals(lat, lgn, 3);

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/countries");
    }


    @Test
    public void theModelShouldHaveTheFoundHospital() {
        Long id = 1L;

        whenHospitalIsFound(id);

        Hospital hospitalFound = (Hospital) getHospitalDetailModel(id).get("hospital");

        Assertions.assertThat(hospitalFound).isNotNull();
    }

    @Test
    public void itShouldRedirectToHomeIfHospitalDetailWasNotFound() {
        Long id = 99L;
        whenHospitalIsNotFound(id);

        var mav = hospitalController.getHospitalDetail(id);

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/home");
    }

    @Test
    public void theModelShouldHaveTheCommentsFromHospital() {
        Long id = 1L;
        whenHospitalIsFound(id);
        whenCommentsAreFound(id);

        List<Comment> comments = (List<Comment>) getHospitalDetailModel(id).get("comments");

        Assertions.assertThat(comments).hasSize(2);
    }

    private Map<String, Object> getHospitalsModel(Double lat, Double lgn, Integer limit) {
        ModelAndView mav = hospitalController.getNearestHospitals(lat, lgn, limit);

        return mav.getModel();
    }

    private Map<String, Object> getHospitalDetailModel(Long id) {
        ModelAndView mav = hospitalController.getHospitalDetail(id);

        return mav.getModel();
    }

    private void whenHospitalsAreReturned(Double lat, Double lgn, Integer limit) {
        when(hospitalService.getNearestHospitalsByLocation(lat, lgn, limit)).thenReturn(
                List.of(new Hospital(), new Hospital(), new Hospital())
        );
    }

    private void whenHospitalIsFound(Long id) {
        when(hospitalService.getHospitalById(id)).thenReturn(new Hospital());
    }

    private void whenHospitalIsNotFound(Long id) {
        when(hospitalService.getHospitalById(id)).thenThrow(new HospitalNotFoundException());
    }

    private void whenAnErrorIsThrow(Double lat, Double lgn, Integer limit) {
        when(hospitalService.getNearestHospitalsByLocation(lat, lgn, limit))
                .thenThrow(new InvalidCoordinatesException(ERROR_MESSAGE));
    }

    private void whenCommentsAreFound(Long hospitalId) {
        when(commentService.getCommentsByHospitalId(hospitalId)).thenReturn(List.of(new Comment(), new Comment()));
    }

}
