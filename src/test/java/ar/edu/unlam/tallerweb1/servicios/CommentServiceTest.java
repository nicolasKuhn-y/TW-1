package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.repositorios.comment.CommentRepository;
import ar.edu.unlam.tallerweb1.servicios.comment.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class CommentServiceTest {
    private CommentRepository commentRepository;
    private CommentService commentService;

    @Before
    public void init() {
        commentRepository = mock(CommentRepository.class);
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void itShouldReturnAllCommentsGivenHospitalId() {
        Long hospitalId = 1L;
        whenCommentsAreFound(hospitalId);

        List<Comment> comments = commentService.getCommentsByHospitalId(hospitalId);

        Assertions.assertThat(comments).hasSize(2);
        Assertions.assertThat(comments.get(0)).isInstanceOf(Comment.class);
    }

    private void whenCommentsAreFound(Long id) {
        when(commentRepository.getCommentsByHospital(id))
                .thenReturn(List.of(new Comment(), new Comment()));
    }


}
