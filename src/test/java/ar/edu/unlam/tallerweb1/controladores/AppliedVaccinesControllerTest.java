package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.servicios.appliedVaccine.AppliedVaccinesService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppliedVaccinesControllerTest {


    private AppliedVaccinesService appliedVaccinesService;
    private AppliedVaccinesController appliedVaccinesController;
    User user = new User();

    @Before
    public void init(){
        appliedVaccinesService=mock(AppliedVaccinesService.class);
        appliedVaccinesController= new AppliedVaccinesController(appliedVaccinesService);
    }

    @Test
    public void queMuestraLasVacunasAplicadas(){
        dadoQueExistenVacunasAplicadas();
        ModelAndView mav = buscarVacunasAplicadas(user);
        porLoTantoMuestroVacunasAplicadas("appliedVaccine",mav.getViewName());
    }

    private void porLoTantoMuestroVacunasAplicadas(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaEsperada).isEqualTo(vistaRecibida);
    }

    private ModelAndView buscarVacunasAplicadas(User user) {
        return appliedVaccinesController.findVaccineApplied(user);
    }

    private void dadoQueExistenVacunasAplicadas() {
        List<Vaccine> vacunasAplicadas = new LinkedList<>();
        vacunasAplicadas.add(new Vaccine("vacuna1",20,1));
        vacunasAplicadas.add(new Vaccine("vacuna2",50,5));
        vacunasAplicadas.add(new Vaccine("vacuna3",80,1));

        when(appliedVaccinesService.findVaccineApplied(user)).thenReturn(vacunasAplicadas);
    }
}
