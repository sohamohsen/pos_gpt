package com.research.model;

public class Cashier {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private CashierStatus status;

    public Cashier(int id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.status = CashierStatus.ACTIVE;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public CashierStatus getStatus() { return status; }

    public void setStatus(CashierStatus status) {
        this.status = status;
    }
}
