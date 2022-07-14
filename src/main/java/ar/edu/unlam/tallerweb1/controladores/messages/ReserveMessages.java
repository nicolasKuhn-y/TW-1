package ar.edu.unlam.tallerweb1.controladores.messages;

public enum ReserveMessages {
    RESERVE_MADE_BEFORE_TODAY("La reserva debe ser hecha con posterioridad al dia de hoy."),
    EMPTY_RESERVE_TIME("La fecha y la hora no deben estar vacias");

    public final String message;

    ReserveMessages(String label) {
        this.message = label;
    }
}
