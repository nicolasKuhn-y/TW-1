package ar.edu.unlam.tallerweb1.validators.errorMessages;

public enum AuthErrorMessages {
    BAD_EMAIL("El email esta mal formateado"),
    BAD_PASSWORD("La contrasenia debe tener 8 caracteres, una mayuscula y una minuscula por lo menos");

    public final String message;

    AuthErrorMessages(String label) {
        this.message = label;
    }

}
