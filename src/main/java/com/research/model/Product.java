package com.research.model;

public class Product {
    private int id;
    private String name;
    private Category category;
    private double price;
    private int stockQuantity;
    private ProductStatus status;

    public Product(int id, String name, Category category, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = stockQuantity > 0 ? ProductStatus.AVAILABLE : ProductStatus.OUT_OF_STOCK;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public ProductStatus getStatus() { return status; }

    public void reduceStock(int qty) {
        this.stockQuantity -= qty;
        if (stockQuantity <= 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }
}
