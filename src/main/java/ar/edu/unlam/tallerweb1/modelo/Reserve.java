package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne()
    private Hospital hospital;

    public Reserve() {
    }

    public Reserve(LocalDateTime date, User user, Hospital hospital) {
        this.date = date;
        this.user = user;
        this.hospital = hospital;
    }

    public static Reserve create(LocalDateTime date, User user, Hospital hospital) {
        return new Reserve(date, user, hospital);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateFormatted() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' hh:mm a"));
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserve reserve = (Reserve) o;
        return Objects.equals(id, reserve.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", date=" + date +
                ", hospital=" + hospital +
                '}';
    }
}
