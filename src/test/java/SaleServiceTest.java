
import com.research.model.*;
import com.research.repository.SaleItemRepository;
import com.research.repository.SaleRepository;
import com.research.service.ProductService;
import com.research.service.SaleService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Sales")
@Feature("Sale Lifecycle")
class SaleServiceTest {

    @Mock
    private SaleRepository saleRepo;

    @Mock
    private SaleItemRepository itemRepo;

    @Mock
    private ProductService productService;

    private SaleService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new SaleService(saleRepo, itemRepo, productService);
    }

    @Test
    @Story("Start sale with inactive cashier")
    void shouldFailWhenCashierInactive() {
        Cashier cashier = new Cashier(1, "Ali", "a@mail.com", "011");
        cashier.setStatus(CashierStatus.INACTIVE);

        Customer customer = new Customer(1, "Client", "012");

        assertThrows(RuntimeException.class,
                () -> service.startSale(1, cashier, customer));
    }
}
