import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.IngredientType;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMockTest {
    String bunName;
    float bunPrice;
    IngredientType ingredientType;
    String ingredientName;
    float ingredientPrice;
    float totalPrice;

    @Mock
    Burger burger;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    public BurgerMockTest() {
        this.bunName = "black bun";
        this.bunPrice = 100;
        this.ingredientType = IngredientType.FILLING;
        this.ingredientName = "Начинка";
        this.ingredientPrice = 50;
        this.totalPrice = 250;
    }

    @Test
    public void getPriceTest() {
        Mockito.when(burger.getPrice()).thenReturn(totalPrice);
        assertEquals("Стоимость бургера не совпадает с ожидаемой", totalPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(burger.getReceipt()).thenReturn(getTestReciept());
        collector.checkThat("Рецепт не содержит добавленный ингредиент", burger.getReceipt(), containsString(ingredientName));
        collector.checkThat("Стоимость бургера не совпадает с ожидаемой", burger.getReceipt(), containsString(String.format("Price: %f", totalPrice)));
    }

    private String getTestReciept() {
        StringBuilder receipt = new StringBuilder("===============\n");
        receipt.append("(==== black bun ====)\n");
        receipt.append("= filling Начинка =\n");
        receipt.append("(==== black bun ====)\n");
        receipt.append("\nPrice: 250,000000\n");
        return receipt.toString();
    }
}