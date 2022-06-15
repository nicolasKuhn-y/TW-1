package ar.edu.unlam.tallerweb1.servicios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;

import java.util.List;

public interface IHospitalService {

   List<Hospital> getNearestHospitalsByLocation(Double latitude, Double longitude, Integer Limit);
}
