package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDateTime;

public class CreateReserveDto {
    private final LocalDateTime dateTime;
    private final Long userId;
    private final Long hospitalId;

    public CreateReserveDto(LocalDateTime dateTime, Long userId, Long hospitalId) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.hospitalId = hospitalId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }
}
