package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
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
            System.out.println("Saved category {" + item.getId() + " " + item.getName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save category {" + item.getId() + " " + item.getName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Category item) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Category category = (Category) session.merge(item);
            session.delete(category);
            session.flush();
            System.out.println("Deleted category {" + item.getId() + " " + item.getName() + "}.");
        } catch (ConstraintViolationException ex) {
            System.out.println("Failed to delete category {" + item.getId() + " " + item.getName() + "}: ConstraintViolationException Exception");
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            System.out.println("Failed to delete category {" + item.getId() + " " + item.getName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Category item) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(item);
            session.flush();
            System.out.println("Updated category {" + item.getId() + " " + item.getName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update category {" + item.getId() + " " + item.getName() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
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
