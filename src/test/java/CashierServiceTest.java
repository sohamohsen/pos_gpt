
import com.research.exception.NotFoundException;
import com.research.model.Cashier;
import com.research.repository.CashierRepository;
import com.research.service.CashierService;
import com.research.service.ValidationService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Epic("Cashier Management")
@Feature("Cashier Service")
class CashierServiceTest {

    @Mock
    private CashierRepository repository;

    private CashierService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new CashierService(repository, new ValidationService());
    }

    @Test
    @Story("Add cashier successfully")
    @Severity(SeverityLevel.CRITICAL)
    void shouldAddCashier() {
        Cashier cashier = new Cashier(1, "Ahmed", "a@mail.com", "010");

        service.addCashier(cashier);

        verify(repository).save(1, cashier);
    }

    @Test
    @Story("Deactivate non-existing cashier")
    @Severity(SeverityLevel.NORMAL)
    void shouldFailWhenCashierNotFound() {
        when(repository.findById(99)).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> service.deactivate(99));
    }
}
