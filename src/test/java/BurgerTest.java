import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;

    private Burger burger;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }


    @Test
    public void moveIngredientTest(){

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0,1);
        assertEquals("ингредиент должны быть перемещены", 1, burger.ingredients.indexOf(firstIngredient));
    }

    @Test
    public void getPriceTest(){
        Mockito.when(bun.getPrice()).thenReturn(15.5F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(10f);

        burger.addIngredient(firstIngredient);
        burger.setBuns(bun);

        assertEquals("некорректная цена", 41f, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptTest(){
        Mockito.when(bun.getName()).thenReturn("Ржаной");
        Mockito.when(bun.getPrice()).thenReturn(15.5F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(10f);
        Mockito.when(firstIngredient.getName()).thenReturn("Cырный");
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        expected.append(String.format("= %s %s =%n", firstIngredient.getType().toString().toLowerCase(),
                firstIngredient.getName()));
        expected.append(String.format("(==== %s ====)%n", bun.getName()));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals("некорректный рецепт",expected.toString(),burger.getReceipt());
    }

}