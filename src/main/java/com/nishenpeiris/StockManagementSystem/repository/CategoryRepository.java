package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.Category;
import com.nishenpeiris.StockManagementSystem.HibernateSpecification;
import com.nishenpeiris.StockManagementSystem.Repository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryRepository implements Repository<Category> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Category item) {
        Session session = sessionFactory.openSession();
        Long itemID = null;
        try {
            itemID = (Long) session.save(item);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        if (itemID != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Category item) {
        Session session = sessionFactory.openSession();
        boolean done = false;
        try {
            session.delete(item);
            done = true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    @Override
    public boolean update(Category item) {
        Session session = sessionFactory.openSession();
        boolean done = false;
        try {
            session.update(item);
            done = true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    @Override
    public List<Category> query(HibernateSpecification specification) {
        return null;
    }
}
