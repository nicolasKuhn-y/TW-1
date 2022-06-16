package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "total_inyections")
    private Integer totalInyections;

    @Column(name = "rest_time")
    private Integer restTime;

    @Column(name = "maxAge")
    private Integer maxAgeApplication;
    @Column(name = "minAge")
    private Integer minAgeApplication;


    public Vaccine() {
    }


    public Integer getMaxAgeApplication() {
        return maxAgeApplication;
    }

    public void setMaxAgeApplication(Integer maxAgeApplication) {
        this.maxAgeApplication = maxAgeApplication;
    }

    public Integer getMinAgeApplication() {
        return minAgeApplication;
    }

    public void setMinAgeApplication(Integer minAgeApplication) {
        this.minAgeApplication = minAgeApplication;
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
