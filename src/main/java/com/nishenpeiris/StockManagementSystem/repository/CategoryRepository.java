package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
    public void add(Category item) throws Exception {
        Session session = sessionFactory.openSession();
        HibernateSpecification specification = new CategorySpecificationByName(item.getName());
        List<Category> list = query(specification);
        if (!list.isEmpty()) {
            System.out.println("Failed to save category: category name - " + item.getName() + " already in use");
            throw new CategoryNameAlreadyInUseException();
        }
        try {
            session.save(item);
            System.out.println("Saved Category {" + item.getId() + " " + item.getName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save Category {" + item.getId() + " " + item.getName() + "}: Hibernate Exception");
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Category item) {
        Session session = sessionFactory.openSession();
        try {
            session.delete(item);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Category item) {
        Session session = sessionFactory.openSession();
        try {
            session.update(item);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
//        return null;
    }

    @Override
    public List<Category> query(HibernateSpecification specification) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Category.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }
}
