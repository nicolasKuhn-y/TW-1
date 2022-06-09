package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.hospital.HospitalService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class HospitalControllerTest {
    private HospitalService hospitalService;
    private HospitalController hospitalController;

    @Before
    public void init() {
        hospitalService = mock(HospitalService.class);
        hospitalController = new HospitalController(hospitalService);
    }


    @Test
    public void theModelShouldHaveAllTheHospitalsSortedByCurrentLocation() {



    }
}
