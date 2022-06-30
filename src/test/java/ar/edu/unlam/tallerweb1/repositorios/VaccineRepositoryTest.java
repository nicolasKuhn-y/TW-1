package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.vaccine.VaccineRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional @Rollback
public class VaccineRepositoryTest extends SpringTest {

    @Autowired
    private VaccineRepository vaccineRepository;


    @Test
    public void queDevuelveToDasLasVacunas(){
        Vaccine vaccine = new Vaccine();

        session().save(vaccine);

        List<Vaccine> list = vaccineRepository.listAllVaccine();

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void queRetornaUnaListaVaciaSiNoHayVacunasCargadasEnLaBDD(){
        List<Vaccine> list = vaccineRepository.listAllVaccine();
        assertThat(list.size()).isEqualTo(0);
    }


}
