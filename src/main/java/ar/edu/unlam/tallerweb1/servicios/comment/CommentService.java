package ar.edu.unlam.tallerweb1.servicios.comment;

import ar.edu.unlam.tallerweb1.controladores.messages.CommentMessages;
import ar.edu.unlam.tallerweb1.exceptions.CommentValidationException;
import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.comment.ICommentRepository;
import ar.edu.unlam.tallerweb1.repositorios.hospital.IHospitalRepository;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
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

        if (!canUserComment(createCommentDto.getUser(), hospitalFound.getName()))
            throw new CommentValidationException(CommentMessages.USER_CANNOT_COMMENT.message);

        var newComment = new Comment(createCommentDto.getDescription(), createCommentDto.getUser(), hospitalFound);

        commentRepository.saveComment(newComment);

        return newComment;
    }

    // Un usuario solo puede comentar si ya tuvo una reserva hecha dias previos a la fecha del comentario
    private boolean canUserComment(User user, String hospitalName) {
        var today = LocalDateTime.now();

        Predicate<Reserve> isBeforeToday = reserve -> reserve.getDate().isBefore(today);
        Predicate<Reserve> isTheSameHospital = reserve -> reserve.getHospital().getName().equals(hospitalName);

        return user.getReserves().stream().anyMatch(isBeforeToday.and(isTheSameHospital));
    }
}
