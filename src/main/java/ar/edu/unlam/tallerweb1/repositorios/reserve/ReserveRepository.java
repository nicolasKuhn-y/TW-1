package ar.edu.unlam.tallerweb1.repositorios.reserve;

import ar.edu.unlam.tallerweb1.modelo.Reserve;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ReserveRepository implements IReserveRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ReserveRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Reserve makeReserve(Reserve reserve) {
        Serializable reserveId = sessionFactory.getCurrentSession().save(reserve);

        return (Reserve) sessionFactory.getCurrentSession()
                .createCriteria(Reserve.class)
                .add(Restrictions.eq("id", reserveId))
                .uniqueResult();
    }

    @Override
    public List<Reserve> getReservesByUser(Long userId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Reserve.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .createAlias("user", "u")
                .add(Restrictions.eq("u.id", userId))
                .list();
    }
}
