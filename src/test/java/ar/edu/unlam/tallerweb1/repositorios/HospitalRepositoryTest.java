package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Rollback
public class HospitalRepositoryTest extends SpringTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    public void itShouldReturnAllHospitals() {
        Hospital hospital1 = new Hospital();
        Hospital hospital2 = new Hospital();
        Hospital hospital3 = new Hospital();

        this.session().save(hospital1);
        this.session().save(hospital2);
        this.session().save(hospital3);


        List<Hospital> hospitalList = hospitalRepository.getAllHospitals();

        Assertions.assertThat(hospitalList).hasSize(3);
    }

//    @Test
//    public void itShouldFoundHospitalById() {
//        Long id = 1L;
//
//        Hospital hospital = new Hospital();
//        hospital.setId(id);
//
//        this.session().save(hospital);
//
//        Hospital hospitalFound = hospitalRepository.getOneHospital(id);
//
//        Assertions.assertThat(hospitalFound.getId()).isEqualTo(hospital.getId());
//    }

}
