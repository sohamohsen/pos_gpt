package com.research;

import com.research.model.Cashier;
import com.research.repository.*;
import com.research.service.*;
import com.research.ui.AdminMenu;
import com.research.ui.CashierMenu;
import com.research.ui.ConsoleHelper;

public class Main {

    public static void main(String[] args) {

        /* ===================== REPOSITORIES ===================== */
        CashierRepository cashierRepository = new CashierRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository = new SaleRepository();
        SaleItemRepository saleItemRepository = new SaleItemRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        ReceiptRepository receiptRepository = new ReceiptRepository();

        /* ===================== SERVICES ===================== */
        ValidationService validationService = new ValidationService();

        CashierService cashierService =
                new CashierService(cashierRepository, validationService);
        CustomerService customerService =
                new CustomerService(customerRepository);
        CategoryService categoryService =
                new CategoryService(categoryRepository);
        ProductService productService =
                new ProductService(productRepository);

        SaleService saleService =
                new SaleService(saleRepository, saleItemRepository, productService);
        PaymentService paymentService =
                new PaymentService(paymentRepository);
        ReceiptService receiptService =
                new ReceiptService(receiptRepository);
        ReportService reportService =
                new ReportService(paymentRepository);

        /* ===================== UI ===================== */
        AdminMenu adminMenu =
                new AdminMenu(cashierService, categoryService, productService, reportService);

        CashierMenu cashierMenu =
                new CashierMenu(
                        saleService,
                        productService,
                        paymentService,
                        receiptService,
                        customerService
                );

        /* ===================== APP LOOP ===================== */
        System.out.println("======================================");
        System.out.println("   POS CASHIER SYSTEM - JAVA 17");
        System.out.println("======================================");

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Administrator");
            System.out.println("2. Cashier");
            System.out.println("0. Exit");

            int choice = ConsoleHelper.readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> adminMenu.start();

                    case 2 -> {
                        int cashierId = ConsoleHelper.readInt("Enter Cashier ID: ");
                        Cashier cashier = cashierService.getById(cashierId);
                        cashierMenu.start(cashier);
                    }

                    case 0 -> {
                        System.out.println("👋 Exiting system...");
                        return;
                    }

                    default -> System.out.println("❌ Invalid option");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
