package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer totalInyections;

    private Integer restTime;

    public Vaccine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalInyections() {
        return totalInyections;
    }

    public void setTotalInyections(Integer totalInyections) {
        this.totalInyections = totalInyections;
    }

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }
}
