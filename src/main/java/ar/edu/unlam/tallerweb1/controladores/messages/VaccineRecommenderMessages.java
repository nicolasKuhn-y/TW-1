package ar.edu.unlam.tallerweb1.controladores.messages;

public enum VaccineRecommenderMessages {
    NOT_FOUND_REQUIRED_VACCINES("No hay vacunas requeridas para entrar al pais"),
    NOT_FOUND_RECOMMENDED_VACCINES("No hay vacunas recomendadas para entrar al pais");

    public final String message;

    VaccineRecommenderMessages(String message) {
        this.message = message;
    }
}
