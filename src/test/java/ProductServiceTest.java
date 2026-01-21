
import com.research.exception.BusinessRuleViolationException;
import com.research.model.Category;
import com.research.model.Product;
import com.research.repository.ProductRepository;
import com.research.service.ProductService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Product Management")
@Feature("Stock Control")
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ProductService(repository);
    }

    @Test
    @Story("Reduce stock normally")
    void shouldReduceStock() {
        Category c = new Category(1, "Food", "Food");
        Product p = new Product(1, "Burger", c, 50, 10);

        service.reduceStock(p, 3);

        assertEquals(7, p.getStockQuantity());
    }

    @Test
    @Story("Reduce stock below zero")
    @Severity(SeverityLevel.BLOCKER)
    void shouldFailWhenStockInsufficient() {
        Category c = new Category(1, "Food", "Food");
        Product p = new Product(1, "Burger", c, 50, 2);

        assertThrows(BusinessRuleViolationException.class,
                () -> service.reduceStock(p, 5));
    }
}
