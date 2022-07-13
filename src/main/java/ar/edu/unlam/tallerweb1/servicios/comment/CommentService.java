package ar.edu.unlam.tallerweb1.servicios.comment;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.repositorios.comment.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {
    private final ICommentRepository commentRepository;

    @Autowired
    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsByHospitalId(Long hospitalId) {
        return commentRepository.getCommentsByHospital(hospitalId);
    }
}
