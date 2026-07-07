import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParamTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerParamTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Расчет цены: {0} {1} {2}, где {0} - цена булки, {1} - цена соуса, {2} - итоговая цена булки (цена булка * 2 + цена соус)")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 100f, 25f, 225f },
                { 0f, 25f, 25f },
                { 100f, 0f, 200f }
        });
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals("Неправильная финальная стоимость бургера", expectedPrice, burger.getPrice(), 0.1);
    }
}