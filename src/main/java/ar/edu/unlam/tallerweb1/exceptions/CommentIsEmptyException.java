package ar.edu.unlam.tallerweb1.exceptions;

public class CommentIsEmptyException extends RuntimeException {
    public CommentIsEmptyException(String message) {
        super(message);
    }
}
