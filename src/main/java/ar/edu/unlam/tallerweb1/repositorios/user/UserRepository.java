package ar.edu.unlam.tallerweb1.repositorios.user;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("repositorioUsuario")
public class UserRepository implements IUserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User find(UserData user) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u " + "WHERE u.email=:email AND u.password=:password", User.class
        );

        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());

        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return (User) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

}
