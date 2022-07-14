package ar.edu.unlam.tallerweb1.servicios.comment.dtos;

import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.Objects;

public class CreateCommentDto {
    private final String description;
    private final User user;
    private final Long hospitalId;

    private CreateCommentDto(String description, User user, Long hospitalId) {
        this.description = description;
        this.user = user;
        this.hospitalId = hospitalId;
    }


    public static CreateCommentDto create(String description, User user, Long hospitalId) {
        return new CreateCommentDto(description, user, hospitalId);
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCommentDto that = (CreateCommentDto) o;
        return Objects.equals(description, that.description) && Objects.equals(user, that.user) && Objects.equals(hospitalId, that.hospitalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, user, hospitalId);
    }
}
