package ar.edu.unlam.tallerweb1.servicios.comment;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import ar.edu.unlam.tallerweb1.servicios.comment.dtos.CreateCommentDto;

import java.util.List;

public interface ICommentService {

    List<Comment> getCommentsByHospitalId(Long hospitalId);

    Comment createComment(CreateCommentDto createCommentDto);
}
