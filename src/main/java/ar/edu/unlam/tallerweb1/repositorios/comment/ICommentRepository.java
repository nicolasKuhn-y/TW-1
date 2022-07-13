package ar.edu.unlam.tallerweb1.repositorios.comment;

import ar.edu.unlam.tallerweb1.modelo.Comment;

import java.util.List;

public interface ICommentRepository {
    Comment saveComment(Comment comment);

    List<Comment> getCommentsByHospital(Long hospitalId);

}
