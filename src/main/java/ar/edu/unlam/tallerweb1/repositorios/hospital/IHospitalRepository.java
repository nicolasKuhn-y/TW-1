package ar.edu.unlam.tallerweb1.repositorios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;

import java.util.List;

public interface IHospitalRepository {
    List<Hospital> getAllHospitals();

    Hospital getOneHospital(Long id);
}
