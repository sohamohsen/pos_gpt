
import com.research.model.*;
import com.research.repository.PaymentRepository;
import com.research.service.PaymentService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Epic("Payments")
@Feature("Payment Validation")
class PaymentServiceTest {

    private PaymentService service;

    @BeforeEach
    void setup() {
        service = new PaymentService(mock(PaymentRepository.class));
    }

    @Test
    @Story("Pay less than total")
    @Severity(SeverityLevel.BLOCKER)
    void shouldFailWhenAmountIsLessThanTotal() {
        Cashier cashier = new Cashier(1, "Cashier", "c@mail.com", "010");
        Customer customer = new Customer(1, "Client", "012");
        Sale sale = new Sale(1, cashier, customer, java.time.LocalDateTime.now());

        sale.complete();

        assertThrows(IllegalStateException.class,
                () -> service.pay(sale, PaymentMethod.CASH, 10));
    }
}
