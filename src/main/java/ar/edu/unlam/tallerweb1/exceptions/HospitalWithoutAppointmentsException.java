package ar.edu.unlam.tallerweb1.exceptions;

public class HospitalWithoutAppointmentsException extends RuntimeException {

    public HospitalWithoutAppointmentsException(String message) {
        super(message);
    }
}
