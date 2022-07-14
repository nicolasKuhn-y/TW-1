package ar.edu.unlam.tallerweb1.controladores.messages;

public enum CommentMessages {
    EMPTY_COMMENT("El comentario no puede estar vacio."),
    COMMENT_CREATION_FAILED("Hubo un error al crear el comentario. Vuelva a intentar"),
    USER_CANNOT_COMMENT("El usuario no esta habilitado para comentar.");

    public final String message;

    CommentMessages(String label) {
        this.message = label;
    }
}
