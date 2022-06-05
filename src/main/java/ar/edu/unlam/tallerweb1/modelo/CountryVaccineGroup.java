package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity(name = "country_vaccine_group")
public class CountryVaccineGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "vaccine_id")
    Vaccine vaccine;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "country_id" )
    Country country;

    @Column(name = "is_vaccine_required")
    private Boolean isVaccineRequired;

    public CountryVaccineGroup() {
    }

    public CountryVaccineGroup(Vaccine vaccine, Country country, Boolean isVaccineRequired) {
        this.vaccine = vaccine;
        this.country = country;
        this.isVaccineRequired = isVaccineRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getVaccineRequired() {
        return isVaccineRequired;
    }

    public void setVaccineRequired(Boolean vaccineRequired) {
        isVaccineRequired = vaccineRequired;
    }

    @Override
    public String toString() {
        return "CountryVaccineGroup{" +
                "id=" + id +
                ", vaccine=" + vaccine +
                ", country=" + country +
                ", isVaccineRequired=" + isVaccineRequired +
                '}';
    }
}
