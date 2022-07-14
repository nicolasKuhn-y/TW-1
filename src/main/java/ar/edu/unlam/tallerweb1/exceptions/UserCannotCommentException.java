package ar.edu.unlam.tallerweb1.exceptions;

public class UserCannotCommentException extends  RuntimeException{

    public UserCannotCommentException(String message) {
        super(message);
    }
}
