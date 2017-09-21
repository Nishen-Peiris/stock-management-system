package com.nishenpeiris.StockManagementSystem;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Product extends AbstractEntity {
    private String name;
    @ManyToOne
    private Category category;
    private boolean reusable;

    public Product(String name, Category category, boolean reusable) {
        this.name = name;
        this.category = category;
        this.reusable = reusable;
    }

    protected Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isReusable() {
        return reusable;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }
}
