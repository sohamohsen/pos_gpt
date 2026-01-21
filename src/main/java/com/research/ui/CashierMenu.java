package com.research.ui;

import com.research.model.*;
import com.research.service.*;

public class CashierMenu {

    private final SaleService saleService;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final ReceiptService receiptService;
    private final CustomerService customerService;
    private Sale currentSale;

    public CashierMenu(SaleService saleService,
                       ProductService productService,
                       PaymentService paymentService,
                       ReceiptService receiptService,
                       CustomerService customerService) {
        this.saleService = saleService;
        this.productService = productService;
        this.paymentService = paymentService;
        this.receiptService = receiptService;
        this.customerService = customerService;
    }

    public void start(Cashier cashier) {
        while (true) {
            System.out.println("\n===== CASHIER MENU =====");
            System.out.println("1. Start Sale");
            System.out.println("2. Add Product");
            System.out.println("3. Complete Sale");
            System.out.println("4. Pay");
            System.out.println("5. Print Receipt");
            System.out.println("0. Back");

            int choice = ConsoleHelper.readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> startSale(cashier);
                    case 2 -> addProduct();
                    case 3 -> saleService.completeSale(currentSale);
                    case 4 -> pay();
                    case 5 -> receiptService.generate(currentSale).print();
                    case 0 -> { return; }
                    default -> System.out.println("❌ Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private void startSale(Cashier cashier) {
        int saleId = ConsoleHelper.readInt("Sale ID: ");
        int customerId = ConsoleHelper.readInt("Customer ID: ");
        Customer customer = customerService.getById(customerId);
        currentSale = saleService.startSale(saleId, cashier, customer);
        System.out.println("✅ Sale started");
    }

    private void addProduct() {
        int productId = ConsoleHelper.readInt("Product ID: ");
        int qty = ConsoleHelper.readInt("Quantity: ");
        Product product = productService.getById(productId);
        saleService.addItem(currentSale, product, qty);
        System.out.println("✅ Item added");
    }

    private void pay() {
        double amount = ConsoleHelper.readDouble("Amount paid: ");
        paymentService.pay(currentSale, PaymentMethod.CASH, amount);
        System.out.println("✅ Payment successful");
    }
}
