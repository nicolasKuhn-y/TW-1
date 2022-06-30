package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.AppliedVaccine;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import ar.edu.unlam.tallerweb1.repositorios.appliedVacccine.AppliedVaccineRepository;
import ar.edu.unlam.tallerweb1.repositorios.appliedVacccine.IAppliedVaccineRepository;
import ar.edu.unlam.tallerweb1.servicios.appliedVaccine.AppliedVaccinesService;
import ar.edu.unlam.tallerweb1.servicios.appliedVaccine.IAppliedVaccinesService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppliedVaccineServiceTest {

    AppliedVaccineRepository appllieVaccineRepository;
    AppliedVaccinesService appliedVaccinesService;

    @Before
    public void init(){
        appllieVaccineRepository = mock(AppliedVaccineRepository.class);
        appliedVaccinesService = new AppliedVaccinesService(appllieVaccineRepository);
    }

  //      @Test
  //      public void queVerificaLaDevolucionDeUnaListaDeVacunasAplicadas(){
 //           dadoQueExistenVacunasAplicadasParaElUsuarioX();
 //           List<Vaccine> listRecibida = buscoVacunasAplicadasA(user);
 //           entoncesVerificoQueLaLista(listRecibida,3);

//        }

    private void dadoQueExistenVacunasAplicadasParaElUsuarioX() {
        List<AppliedVaccine> applied = new LinkedList<>();
        applied.add(new AppliedVaccine(1,1));
        applied.add(new AppliedVaccine(1,2));
        applied.add(new AppliedVaccine(1,3));
   //     when(IAppliedVaccinesService.).thenReturn(applied);
    }

}
