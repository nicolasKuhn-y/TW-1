package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class AppliedVaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int id_user;

    private int id_vaccine;

    public AppliedVaccine(Integer id_user, Integer id_vaccine) {
        this.id_user=id_user;
        this.id_vaccine=id_vaccine;
    }

    public AppliedVaccine() {

    }

    public int getId_vaccine() {
        return id_vaccine;
    }

    public void setId_vaccine(int id_vaccine) {
        this.id_vaccine = id_vaccine;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
