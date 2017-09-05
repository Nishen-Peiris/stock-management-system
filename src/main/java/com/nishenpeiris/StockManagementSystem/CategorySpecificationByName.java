package com.nishenpeiris.StockManagementSystem;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class CategorySpecificationByName implements HibernateSpecification {
    private String desiredName;

    public CategorySpecificationByName(String desiredName) {
        super();
        this.desiredName = desiredName;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("name", desiredName);
    }
}
