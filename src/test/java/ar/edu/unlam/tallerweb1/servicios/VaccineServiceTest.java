package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.hospital.HospitalRepository;
import ar.edu.unlam.tallerweb1.repositorios.vaccine.IVaccineRepository;
import ar.edu.unlam.tallerweb1.repositorios.vaccine.VaccineRepository;
import ar.edu.unlam.tallerweb1.servicios.hospital.HospitalService;
import ar.edu.unlam.tallerweb1.servicios.vaccine.VaccineService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VaccineServiceTest {

    private VaccineRepository vaccineRepository;
    private VaccineService vaccineService;
    LocalDate fechaNac = LocalDate.of(1996, Month.MAY,18);


    @Before
    public void init() {
        vaccineRepository = mock(VaccineRepository.class);
        vaccineService = new VaccineService(vaccineRepository);
    }

    @Test
    public void queDevuelveTodasLasVacunas(){
        dadoQueExistenDosVacunas();
        List<Vaccine> listVacunas = cuandoBuscoTodasLasVacunas();
        entoncesDevuelvoLasDosVacunasEnUnaLista(listVacunas,2);
    }

    @Test
    public void queDevuelveLasVacunasRecomendadasALaEdadIngresada(){
        dadoQueExistenVacunas();
        List<Vaccine> listVacunasRecomendades = cuandoBuscoVacunasRecomendadasPorEdad(fechaNac);
        entoncesObtengoUnaListaConDosVacunasRecomendadas(listVacunasRecomendades,2);

    }

    private void entoncesObtengoUnaListaConDosVacunasRecomendadas(List<Vaccine> listVacunasRecomendades, int cantidadDeVacunasRecomendadas) {
        assertThat(listVacunasRecomendades.size()).isEqualTo(cantidadDeVacunasRecomendadas);
    }

    private List<Vaccine> cuandoBuscoVacunasRecomendadasPorEdad(LocalDate fechaNac) {
        return vaccineService.findVaccinesRecommendedByAge(fechaNac);
    }

    private void dadoQueExistenVacunas() {
        List<Vaccine> list = new LinkedList<>();
        list.add(new Vaccine("Antirrabica",65, 7));
        list.add(new Vaccine("COVID-19", 90, 13));
        list.add(new Vaccine("COVID-191", 20, 13));
        when(vaccineService.findVaccinesRecommendedByAge(fechaNac)).thenReturn(list);
    }

    private void entoncesDevuelvoLasDosVacunasEnUnaLista(List<Vaccine> listVacunas, int cantidadEnListaRecibida) {
        assertThat(listVacunas.size()).isEqualTo(cantidadEnListaRecibida);
    }

    private List<Vaccine> cuandoBuscoTodasLasVacunas() {
        return vaccineService.findAllVaccine();
    }

    private void dadoQueExistenDosVacunas() {
        List<Vaccine> list = new LinkedList<>();
        list.add(new Vaccine());
        list.add(new Vaccine());
        when(vaccineService.findAllVaccine()).thenReturn(list);
    }

}
