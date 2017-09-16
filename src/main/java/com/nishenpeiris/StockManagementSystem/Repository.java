package com.nishenpeiris.StockManagementSystem;

import java.util.List;

public interface Repository<T> {
    void add(T item) throws Exception;

    void remove(T item) throws Exception;

    void update(T item) throws Exception;

    List<T> query(HibernateSpecification specification) throws Exception;
}
