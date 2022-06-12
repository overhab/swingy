package edu.school21.dao;

import edu.school21.app.HibernateSessionFactoryUtil;
import edu.school21.models.Inventory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDao {

    public List<Inventory> findAllItems(long heroId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Inventory> query = session.createQuery("from Inventory where heroId=:heroId", Inventory.class);
        query.setParameter("heroId", heroId);
        return query.list();
    }

    public void addNewItem(Inventory inventory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(inventory);
        transaction.commit();
        session.close();
    }

    public void removeItem(long heroId, long itemId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createNativeQuery(
                "delete from inventory where hero_id=:heroId and item_id=:itemId");
        query.setParameter("heroId", heroId);
        query.setParameter("itemId", itemId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
