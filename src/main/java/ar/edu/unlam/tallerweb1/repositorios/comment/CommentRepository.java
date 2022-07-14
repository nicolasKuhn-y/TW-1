package ar.edu.unlam.tallerweb1.repositorios.comment;

import ar.edu.unlam.tallerweb1.modelo.Comment;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CommentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Comment saveComment(Comment comment) {
        Serializable commentId = sessionFactory.getCurrentSession().save(comment);

        return (Comment) sessionFactory.getCurrentSession()
                .createCriteria(Comment.class)
                .add(Restrictions.eq("id", commentId))
                .uniqueResult();
    }

    @Override
    public List<Comment> getCommentsByHospital(Long hospitalId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Comment.class)
                .createAlias("hospital", "h")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("h.id", hospitalId))
                .list();
    }
}
