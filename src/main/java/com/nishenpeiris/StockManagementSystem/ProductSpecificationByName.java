package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ProductSpecificationByName implements HibernateSpecification {
    private String desiredName;

    public ProductSpecificationByName(String desiredName) {
        super();
        this.desiredName = desiredName;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("name", desiredName);
    }
}
