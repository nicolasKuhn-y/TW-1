package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.comment.CommentRepository;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.servicios.comment.CommentService;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    private CommentRepository commentRepository;
    private HospitalRepository hospitalRepository;
    private CommentService commentService;

    @Before
    public void init() {
        commentRepository = mock(CommentRepository.class);
        hospitalRepository = mock(HospitalRepository.class);
        commentService = new CommentService(commentRepository, hospitalRepository);
    }

    @Test
    public void itShouldReturnAllCommentsGivenHospitalId() {
        Long hospitalId = 1L;
        whenCommentsAreFound(hospitalId);

        List<Comment> comments = commentService.getCommentsByHospitalId(hospitalId);

        Assertions.assertThat(comments).hasSize(2);
        Assertions.assertThat(comments.get(0)).isInstanceOf(Comment.class);
    }

    @Test
    public void itShouldReturnAllCommentsOrderedDESC() {
        Long hospitalId = 1L;
        whenCommentsAreFound(hospitalId);

        List<Comment> comments = commentService.getCommentsByHospitalId(hospitalId);

        var newerTime = comments.get(1).getCreatedAt();
        var olderTime = comments.get(0).getCreatedAt();

        Assertions.assertThat(newerTime).isLessThan(olderTime);
    }

    @Test
    public void itShouldCreateAComment() {
        var user = new User();
        var hospitalId = 1L;
        var description = "Un comentario";
        whenCommentCreationEntitiesAreFound(hospitalId);

        var createCommentDto = CreateCommentDto.create(description, user, hospitalId);

        thenCommentIsCreated();
        var commentCreated = commentService.createComment(createCommentDto);

        Assertions.assertThat(commentCreated.getDescription()).isEqualTo(description);
    }


    private void whenCommentsAreFound(Long id) {
        var comment1 = createComment(LocalDateTime.now().minusDays(2));
        var comment2 = createComment(LocalDateTime.now());

        when(commentRepository.getCommentsByHospital(id)).thenReturn(List.of(comment1, comment2));
    }

    private void thenCommentIsCreated() {
        var hospital = new Hospital();
        var description = "Un comentario";
        var user = new User();

        var comment = new Comment(description, user, hospital);

        when(commentRepository.saveComment(comment)).thenReturn(comment);
    }

    private void whenCommentCreationEntitiesAreFound(Long hospitalId) {
        when(hospitalRepository.getOneHospital(hospitalId)).thenReturn(new Hospital());
    }

    private Comment createComment(LocalDateTime time) {
        var comment = new Comment();

        comment.setCreatedAt(time);

        return comment;
    }

}
