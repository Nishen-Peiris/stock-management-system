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
public class ProductRepository implements Repository<Product> {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Product product) throws Exception {
        Session session = sessionFactory.openSession();
        HibernateSpecification specification = new ProductSpecificationByName(product.getName());
        List<Category> list = query(specification);
        if (!list.isEmpty()) {
            System.out.println("Failed to save product: product name - " + product.getName() + " already in use");
            throw new ProductNameAlreadyInUseException();
        }
        try {
            session.save(product);
            System.out.println("Saved product {" + product.getId() + " " + product.getName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to save product {" + product.getId() + " " + product.getName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Product product) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Product productToBeRemoved = (Product) session.merge(product);
            session.delete(productToBeRemoved);
            session.flush();
            System.out.println("Deleted product {" + product.getId() + " " + product.getName() + "}.");
        } catch (Exception ex) {
            System.out.println("Failed to delete product {" + product.getId() + " " + product.getName() + "}: Hibernate Exception");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Product product) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.update(product);
            session.flush();
            System.out.println("Updated product {" + product.getId() + " " + product.getName() + "}.");
        } catch (HibernateException ex) {
            System.out.println("Failed to update category {" + product.getId() + " " + product.getName() + "}.");
            ex.printStackTrace();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public List query(HibernateSpecification specification) throws Exception {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        if (specification != null) {
            criteria.add(specification.toCriteria());
        }
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }
}
