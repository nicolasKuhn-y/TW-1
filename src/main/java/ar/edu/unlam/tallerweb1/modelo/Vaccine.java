package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

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

    private Integer maxAge;/*edad maxima para la aplicacion*/

    private Integer minAge;/*edad minima para la aplicacion*/

    public Vaccine() {
    }

    public Vaccine(String name,int maxAge, int minAge) {
        this.name=name;
        this.minAge=minAge;
        this.maxAge=maxAge;
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
    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }
}
