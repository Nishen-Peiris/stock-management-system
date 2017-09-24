package com.nishenpeiris.StockManagementSystem.repository;

import com.nishenpeiris.StockManagementSystem.HibernateSpecification;
import com.nishenpeiris.StockManagementSystem.Repository;
import com.nishenpeiris.StockManagementSystem.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository implements Repository<User> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.save(user);
            System.out.println("Saved user {" + user.getId() + " " + user.getEmail() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save user {" + user.getId() + " " + user.getEmail() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(User user) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            User userToBeRemoved = (User) session.merge(user);
            session.delete(userToBeRemoved);
            session.flush();
            System.out.println("Deleted user {" + user.getId() + " " + user.getEmail() + "}.");
        } catch (Exception ex) {
            System.out.println("Failed to delete user {" + user.getId() + " " + user.getEmail() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(user);
            session.flush();
            System.out.println("Updated user {" + user.getId() + " " + user.getEmail() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update user {" + user.getId() + " " + user.getEmail() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> query(HibernateSpecification specification) throws Exception {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("firstName"))
                .addOrder(Order.asc("lastName"));
        return criteria.list();
    }
}
