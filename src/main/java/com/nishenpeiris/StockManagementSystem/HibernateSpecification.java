package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;

public interface HibernateSpecification {
    Criterion toCriteria();
}
