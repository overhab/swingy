package edu.school21.dao;

import edu.school21.app.HibernateSessionFactoryUtil;
import edu.school21.models.Hero;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HeroDao {

    public Optional<Hero> findById(long id) {
        return Optional.of(HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Hero.class, id));
    }

    public Hero findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Hero> query= session.createQuery("from Hero where name=:name", Hero.class);
        query.setParameter("name", name);
        return (Hero) query.uniqueResult();
    }

    public void save(Hero hero) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(hero);
        transaction.commit();
        session.close();
    }

    public void update(Hero hero) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(hero);
        transaction.commit();
        session.close();
    }

    public void delete(Hero hero) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(hero);
        transaction.commit();
        session.close();
    }

    public List<Hero> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<Hero> query = session.createQuery("from Hero", Hero.class);
        List<Hero> list = query.list();
        tx.commit();
        return list;
    }
}
