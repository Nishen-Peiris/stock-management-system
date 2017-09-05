package com.nishenpeiris.StockManagementSystem;

import java.util.List;

public interface Repository<T> {
    boolean add(T item);

    boolean remove(T item);

    boolean update(T item);

    List<T> query(HibernateSpecification specification);
}
