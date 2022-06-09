package ar.edu.unlam.tallerweb1.controladores.messages;

public enum CountryMessages {
    NOT_FOUND_REQUIRED_VACCINES("No hay vacunas requeridas para entrar al pais."),
    NOT_FOUND_RECOMMENDED_VACCINES("No hay vacunas recomendadas para entrar al pais."),
    NOT_COUNTRY_FOUND("El pais buscado no se encuentra disponible.");

    public final String message;

    CountryMessages(String label) {
        this.message = label;
    }
}
