package ar.edu.unlam.tallerweb1.servicios.appliedVaccine;

import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

public interface IAppliedVaccinesService {

    public List findVaccineApplied(User user);
}
