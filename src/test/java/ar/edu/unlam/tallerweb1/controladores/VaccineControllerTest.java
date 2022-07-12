package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.vaccine.VaccineService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VaccineControllerTest {

    private VaccineService vaccineService;
    private VaccineController vaccineController;
    Integer anioNac = 1996;

    @Before
    public void init(){
        vaccineService=mock(VaccineService.class);
        vaccineController= new VaccineController(vaccineService);

    }

    @Test
    public void queDevuelveTodasLasVacunas(){
        dadoQueExistenVacunas();
        ModelAndView mav = buscarTodasLasVacunas();
        entoncesMuestroTodasLasVacunasEnUnaVista("/all-vaccine",mav.getViewName());
    }

    @Test
    public void queDevuelveVacunasPorFactorEdad(){
        dadoQueExistenVacunasParaBuscarPorFactor();
        ModelAndView mav = buscarVacunarParaEdad(anioNac);
        entoncesMuestroLasVacunasRecomendadasALaEdad("/vaccine-recommended-by-age",mav.getViewName());
    }

    private void dadoQueExistenVacunasParaBuscarPorFactor() {
        List<Vaccine> list = new LinkedList<>();
        list.add(new Vaccine("Antirrabica",65, 7));
        list.add(new Vaccine("COVID-19", 90, 13));
        list.add(new Vaccine("COVID-191", 90, 13));
        when(vaccineService.findVaccinesRecommendedByAge(anioNac)).thenReturn(list);
    }

    private void entoncesMuestroLasVacunasRecomendadasALaEdad(String VistaEsperada, String VistaRecibida) {
        assertThat(VistaEsperada).isEqualTo(VistaRecibida);
    }

    private ModelAndView buscarVacunarParaEdad(Integer anioNac){
        return vaccineController.listVaccineRecommendedByAge(anioNac);
    }

    private void entoncesMuestroTodasLasVacunasEnUnaVista(String VistaEsperada, String VistaRecibida) {
        assertThat(VistaEsperada).isEqualTo(VistaRecibida);
    }

    private ModelAndView buscarTodasLasVacunas() {
        return vaccineController.listAllVaccine();
    }

    private void dadoQueExistenVacunas() {
        List<Vaccine> list = new LinkedList<>();
        list.add(new Vaccine());
        when(vaccineService.findAllVaccine()).thenReturn(list);
    }
}