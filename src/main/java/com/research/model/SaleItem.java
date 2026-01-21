package com.research.model;

public class SaleItem {

    private int id;
    private int sale;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    public SaleItem(int id, int sale, Product product, int quantity) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.subTotal = unitPrice * quantity;
    }

    public int getId() {
        return id;
    }

    public int getSale() {     // ✅ REQUIRED
        return sale;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }
}
