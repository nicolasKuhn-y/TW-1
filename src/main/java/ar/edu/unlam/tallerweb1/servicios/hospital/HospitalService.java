package ar.edu.unlam.tallerweb1.servicios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.utils.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HospitalService implements IHospitalService {
    private final HospitalRepository hospitalRepository;


    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @Override
    public List<Hospital> findAllHospitals() {
        return hospitalRepository.getAllHospitals();
    }

    @Override
    public List<Hospital> getNearestHospitalsByLocation(Double latitude, Double longitude, Integer limit) {
        int defaultLimit = 3;

        if (limit == null)
            limit = defaultLimit;

        Location currentLocation = Location.createWithCoordinates(latitude, longitude);

        return new ArrayList<>(hospitalRepository.getAllHospitals())
                .stream()
                .sorted(new DistanceComparator(currentLocation))
                .limit(limit)
                .collect(Collectors.toList());
    }

}
