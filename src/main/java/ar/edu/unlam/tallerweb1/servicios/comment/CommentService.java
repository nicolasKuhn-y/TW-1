package ar.edu.unlam.tallerweb1.servicios.comment;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.repositorios.comment.ICommentRepository;
import ar.edu.unlam.tallerweb1.repositorios.hospital.IHospitalRepository;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService implements ICommentService {
    private final ICommentRepository commentRepository;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public CommentService(
            ICommentRepository commentRepository,
            IHospitalRepository hospitalRepository
    ) {
        this.commentRepository = commentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Comment> getCommentsByHospitalId(Long hospitalId) {
        return commentRepository.getCommentsByHospital(hospitalId)
                .stream()
                .sorted((Comparator.comparing(Comment::getCreatedAt).reversed()))
                .collect(Collectors.toList());
    }

    @Override
    public Comment createComment(CreateCommentDto createCommentDto) {
        var hospitalFound = hospitalRepository.getOneHospital(createCommentDto.getHospitalId());

        var newComment = new Comment(createCommentDto.getDescription(), createCommentDto.getUser(), hospitalFound);

        commentRepository.saveComment(newComment);

        return newComment;
    }
}
