package ar.edu.unlam.tallerweb1.repositorios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalRepository implements IHospitalRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HospitalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return  (List<Hospital>) sessionFactory.getCurrentSession()
                .createCriteria(Hospital.class)
                .list();
    }
}
