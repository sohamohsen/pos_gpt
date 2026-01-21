package com.research.ui;

import com.research.model.*;
import com.research.service.*;

public class AdminMenu {

    private final CashierService cashierService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ReportService reportService;

    public AdminMenu(CashierService cashierService,
                     CategoryService categoryService,
                     ProductService productService,
                     ReportService reportService) {
        this.cashierService = cashierService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.reportService = reportService;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Cashier");
            System.out.println("2. View Cashiers");
            System.out.println("3. Add Category");
            System.out.println("4. Add Product");
            System.out.println("5. View Products");
            System.out.println("6. View Total Revenue");
            System.out.println("0. Back");

            int choice = ConsoleHelper.readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> addCashier();
                    case 2 -> cashierService.getAll()
                            .forEach(c -> System.out.println(c.getId() + " - " + c.getFullName()));
                    case 3 -> addCategory();
                    case 4 -> addProduct();
                    case 5 -> productService.getAll()
                            .forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " | " + p.getPrice()));
                    case 6 -> System.out.println("💰 Total Revenue: " + reportService.totalRevenue());
                    case 0 -> { return; }
                    default -> System.out.println("❌ Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private void addCashier() {
        int id = ConsoleHelper.readInt("ID: ");
        String name = ConsoleHelper.readString("Full name: ");
        String email = ConsoleHelper.readString("Email: ");
        String phone = ConsoleHelper.readString("Phone: ");
        cashierService.addCashier(new Cashier(id, name, email, phone));
        System.out.println("✅ Cashier added");
    }

    private void addCategory() {
        int id = ConsoleHelper.readInt("ID: ");
        String name = ConsoleHelper.readString("Name: ");
        String desc = ConsoleHelper.readString("Description: ");
        categoryService.add(new Category(id, name, desc));
        System.out.println("✅ Category added");
    }

    private void addProduct() {
        int id = ConsoleHelper.readInt("ID: ");
        String name = ConsoleHelper.readString("Name: ");
        double price = ConsoleHelper.readDouble("Price: ");
        int qty = ConsoleHelper.readInt("Stock quantity: ");

        int categoryId = ConsoleHelper.readInt("Category ID: ");
        Category category = categoryService.getById(categoryId);

        productService.add(new Product(id, name, category, price, qty));
        System.out.println("✅ Product added");
    }
}
