package ar.edu.unlam.tallerweb1.repositorios.vaccine;


import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class VaccineRepository implements IVaccineRepository {
    private final SessionFactory sessionFacctory;

    @Autowired
    public VaccineRepository(SessionFactory sessionFacctory) {
        this.sessionFacctory = sessionFacctory;
    }

    @Override
    public List listAllVaccine() {
        return sessionFacctory.getCurrentSession()
                .createCriteria(Vaccine.class).list();
    }

}
