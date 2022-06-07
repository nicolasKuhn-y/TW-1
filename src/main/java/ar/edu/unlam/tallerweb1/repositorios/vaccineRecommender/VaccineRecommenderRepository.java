package ar.edu.unlam.tallerweb1.repositorios.vaccineRecommender;

import ar.edu.unlam.tallerweb1.modelo.Country;
import ar.edu.unlam.tallerweb1.modelo.CountryVaccineGroup;
import ar.edu.unlam.tallerweb1.modelo.Vaccine;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class VaccineRecommenderRepository implements IVaccineRecommenderRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public VaccineRecommenderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Set<Vaccine> getRequiredVaccines(String code) {
        final Session session = sessionFactory.getCurrentSession();

        Country countryFound = (Country) session.createCriteria(Country.class)
                .add(Restrictions.eq("code", code))
                .uniqueResult();

        if (countryFound == null) return new HashSet<>();

        return countryFound.getVaccineGroups()
                .stream()
                .filter(item -> item.isVacciceRequired().equals(Boolean.TRUE))
                .map(CountryVaccineGroup::getVaccine)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Vaccine> getRecommendedVaccines(String code) {
        final Session session = sessionFactory.getCurrentSession();

        Country countryFound = (Country) session.createCriteria(Country.class)
                .add(Restrictions.eq("code", code))
                .uniqueResult();

        if (countryFound == null) return new HashSet<>();

        return countryFound.getVaccineGroups()
                .stream()
                .filter(item -> item.isVacciceRequired().equals(Boolean.FALSE))
                .map(CountryVaccineGroup::getVaccine)
                .collect(Collectors.toSet());
    }

    @Override
    public List<Country> getCountries() {
        return (List<Country>) sessionFactory.getCurrentSession()
                .createCriteria(Country.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

}