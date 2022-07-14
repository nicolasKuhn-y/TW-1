package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.repositorios.comment.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Rollback
public class CommentRepositoryTest extends SpringTest {
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void itShouldSaveANewComment() {
        var comment = new Comment();

        this.session().save(comment);

        var commentSaved = commentRepository.saveComment(comment);

        Assertions.assertThat(commentSaved).isNotNull();
    }


    @Test
    public void itShouldReturnAListWithAllCommentsFromAnHospital() {
        var hospital = createHospital();
        var comment1 = createCommentWithHospital(hospital);
        var comment2 = createCommentWithHospital(hospital);


        this.session().save(hospital);
        this.session().save(comment1);
        this.session().save(comment2);

        List<Comment> comments = commentRepository.getCommentsByHospital(hospital.getId());


        Assertions.assertThat(comments).hasSize(2);
    }


    private Hospital createHospital() {
        Hospital hospital = new Hospital();
        hospital.setId(3L);
        return hospital;
    }

    private Comment createCommentWithHospital(Hospital hospital) {
        var comment = new Comment();

        comment.setHospital(hospital);

        return comment;
    }
}
