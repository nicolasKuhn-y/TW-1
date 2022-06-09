package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2)
    private String code;

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

    public Set<CountryVaccineGroup> getVaccineGroups() {
        return vaccineGroups;
    }

    public void setVaccineGroups(Set<CountryVaccineGroup> vaccineGroups) {
        this.vaccineGroups = vaccineGroups;
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
