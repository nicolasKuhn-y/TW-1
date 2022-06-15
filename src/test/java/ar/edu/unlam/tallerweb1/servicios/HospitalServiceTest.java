package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.servicios.hospital.HospitalService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HospitalServiceTest {
    private HospitalRepository hospitalRepository;
    private HospitalService hospitalService;

    private final String nearestHospitalName = "Barrio";

    @Before
    public void init() {
        hospitalRepository = mock(HospitalRepository.class);
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    public void itShouldReturnTheHospitalsThatAreNearCurrentLocation() {
        Double lat = -34.665663289716925;
        Double lgn = -58.60524767319679;

        whenThereHospitalsNearMe();

        Hospital nearestHospital = hospitalService.getNearestHospitalsByLocation(lat, lgn, 5).get(0);

        Assertions.assertThat(nearestHospital.getName()).isEqualTo(nearestHospitalName);
    }

    @Test
    public void itShouldReturnOnlyTheFirstThreeHospitalsByDefault() {
        Double lat = -34.665663289716925;
        Double lgn = -58.60524767319679;

        whenThereHospitalsNearMe();

        List<Hospital> hospitals = hospitalService.getNearestHospitalsByLocation(lat, lgn, null);

        Assertions.assertThat(hospitals).hasSize(3);
    }

    @Test
    public void itShouldReturnTheHospitalsWithLimitApplied() {
        Double lat = -34.665663289716925;
        Double lgn = -58.60524767319679;

        whenThereHospitalsNearMe();

        List<Hospital> hospitals = hospitalService.getNearestHospitalsByLocation(lat, lgn, 1);

        Assertions.assertThat(hospitals).hasSize(1);
    }


    private void whenThereHospitalsNearMe() {
        Hospital hospital1 = createHospital(-34.6666036949306, -58.60643375867638, nearestHospitalName);
        Hospital hospital2 = createHospital(-34.54669400495503, -58.520319676909345, "Capital");
        Hospital hospital3 = createHospital(-8.746661859434038, -46.80242941521118, "Lejano");
        Hospital hospital4 = createHospital(55.091670368735905, -62.796766101995935, "Muy muy Lejano");

        List<Hospital> hospitalList = List.of(hospital3, hospital1, hospital2, hospital4);

        when(hospitalRepository.getAllHospitals()).thenReturn(hospitalList);
    }

    private Hospital createHospital(double lat, double lng, String name) {
        Hospital hospital = new Hospital();

        hospital.setName(name);
        hospital.setLatitude(lat);
        hospital.setLongitude(lng);

        return hospital;
    }

}
