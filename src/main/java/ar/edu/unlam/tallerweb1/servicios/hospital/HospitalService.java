package ar.edu.unlam.tallerweb1.servicios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.repositorios.hospital.DistanceComparator;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.utils.Location;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HospitalService implements IHospitalService {
    private final HospitalRepository hospitalRepository;


    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getNearestHospitalsByLocation(Double latitude, Double longitude) {
        Location currentLocation = Location.createWithCoordinates(latitude, longitude);

        List<Hospital> hospitals = new ArrayList<>(hospitalRepository.getAllHospitals());

        hospitals.sort(new DistanceComparator(currentLocation));

        return hospitals;
    }

}
