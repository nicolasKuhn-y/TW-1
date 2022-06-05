package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2)
    private String code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "country_vaccine",
            joinColumns =   @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "vaccine_id")
    )
    private List<Vaccine> vaccinesRequired;


    @OneToMany(mappedBy = "country")
    private Set<CountryVaccineGroup> vaccineGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vaccine> getVaccinesRequired() {
        return vaccinesRequired;
    }

    public void setVaccinesRequired(List<Vaccine> vaccinesRequired) {
        this.vaccinesRequired = vaccinesRequired;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
