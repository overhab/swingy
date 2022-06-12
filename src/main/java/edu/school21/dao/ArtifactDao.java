package edu.school21.dao;

import edu.school21.app.HibernateSessionFactoryUtil;
import edu.school21.models.Artifact;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ArtifactDao {

    public Artifact findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Artifact.class, id);
    }

    public Artifact findByTier(int tier) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Artifact> query= session.createQuery("from Artifact where tier=:tier order by random()", Artifact.class);
        query.setParameter("tier", tier);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
