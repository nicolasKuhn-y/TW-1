package ar.edu.unlam.tallerweb1.repositorios.country;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository implements ICountryRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CountryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vaccine> getRequiredVaccines(String code) {
        final Session session = sessionFactory.getCurrentSession();

        Country countryFound = (Country) session.createCriteria(Country.class)
                .add(Restrictions.eq("code", code))
                .uniqueResult();

        if (countryFound == null) return new ArrayList<>();

        return countryFound.getVaccinesRequired();
    }

    @Override
    public List<Country> getCountries() {
        final Session session = sessionFactory.getCurrentSession();

        return (List<Country>) session.createCriteria(Country.class).list();
    }

}
