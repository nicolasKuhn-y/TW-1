package ar.edu.unlam.tallerweb1.repositorios.reserve.dtos;

import ar.edu.unlam.tallerweb1.modelo.User;

import java.time.LocalDateTime;

public class CreateReserveDto {
    private final LocalDateTime dateTime;
    private final User user;
    private final Long hospitalId;

    public CreateReserveDto(LocalDateTime dateTime, User user, Long hospitalId) {
        this.dateTime = dateTime;
        this.user = user;
        this.hospitalId = hospitalId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public Long getHospitalId() {
        return hospitalId;
    }
}
