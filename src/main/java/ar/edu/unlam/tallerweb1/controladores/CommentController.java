package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.CommentCreationErrorException;
import ar.edu.unlam.tallerweb1.exceptions.CommentIsEmptyException;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.comment.ICommentService;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class CommentController {
    private final ICommentService commentService;
    private final HttpSession session;

    @Autowired
    public CommentController(ICommentService commentService, HttpSession session) {
        this.commentService = commentService;
        this.session = session;
    }


    @RequestMapping("/comments/{hospitalId}")
    public ModelAndView createCommentInHospitalDetail(
            @PathVariable("hospitalId") Long hospitalId,
            @RequestParam("description") String description,
            final RedirectAttributes redirectAttributes
    ) {
        try {
            if (description.trim().length() == 0)
                throw new CommentIsEmptyException("El comentario no puede estar vacio");

            User user = (User) session.getAttribute("user");

            if (user == null) return new ModelAndView("redirect:/login");

            var createCommentDto = CreateCommentDto.create(description, user, hospitalId);

            var comment = commentService.createComment(createCommentDto);

            if (comment == null) {
                throw new CommentCreationErrorException("Hubo un error al crear el comentario. Vuelva a intentar");
            }

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        } catch (CommentIsEmptyException | CommentCreationErrorException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());

            return new ModelAndView("redirect:/hospitals/" + hospitalId);
        }
    }
}
