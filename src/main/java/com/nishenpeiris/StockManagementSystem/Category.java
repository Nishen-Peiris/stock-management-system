package com.nishenpeiris.StockManagementSystem;

import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    protected Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
