package com.research.model;

public class Category {
    private int id;
    private String name;
    private String description;
    private CategoryStatus status;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = CategoryStatus.ACTIVE;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public CategoryStatus getStatus() { return status; }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }
}
