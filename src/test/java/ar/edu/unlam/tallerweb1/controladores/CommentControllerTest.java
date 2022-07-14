package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.comment.CommentService;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentControllerTest {
    private CommentService commentService;
    private HttpSession session;
    private CommentController commentController;

    @Before
    public void init() {
        commentService = mock(CommentService.class);
        session = mock(HttpSession.class);

        commentController = new CommentController(commentService, session);
    }

    @Test
    public void itShouldRedirectToHospitalIfDescriptionIsEmpty() {
        var hospitalId = 1L;
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        var mav = commentController.createCommentInHospitalDetail(
                hospitalId, "", redirectAttributes
        );

        String errorMessage = (String) redirectAttributes.getFlashAttributes().get("error");

        Assertions.assertThat(errorMessage).isEqualTo("El comentario no puede estar vacio");
        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/hospitals/" + hospitalId);
    }

    @Test
    public void itShouldRedirectToLoginIfUserIsNotLogged() {
        var hospitalId = 1L;
        var description = "Un comentario";
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        var mav = commentController.createCommentInHospitalDetail(
                hospitalId, description, redirectAttributes
        );

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void itShouldRedirectToHospitalDetailIfCommentWasCreated() {
        var hospitalId = 1L;
        var description = "Un comentario";
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        whenUserIsLogged();

        var mav = commentController.createCommentInHospitalDetail(
                hospitalId, description, redirectAttributes
        );

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/hospitals/" + hospitalId);
    }

    @Test
    public void itShouldRedirectToHospitalDetailWithErrorIfCommentCouldntBeCreated() {
        var hospitalId = 1L;
        var description = "Un comentario";
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        whenUserIsLogged();

        thenCommentWasNotCreated();

        var mav = commentController.createCommentInHospitalDetail(
                hospitalId, description, redirectAttributes
        );

        String errorMessage = (String) redirectAttributes.getFlashAttributes().get("error");

        Assertions.assertThat(mav.getViewName()).isEqualTo("redirect:/hospitals/" + hospitalId);
        Assertions.assertThat(errorMessage).isEqualTo("Hubo un error al crear el comentario. Vuelva a intentar");
    }


    private void whenUserIsLogged() {
        when(session.getAttribute("user")).thenReturn(new User());
    }


    private void thenCommentWasNotCreated() {
        when(commentService.createComment(CreateCommentDto.create(null, null, null)))
                .thenReturn(null);
    }
}
