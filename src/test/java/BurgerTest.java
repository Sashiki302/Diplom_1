
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals("Булка не попала в бургере", bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertTrue("ingredient1 не добавлен в список бургера", burger.ingredients.contains(ingredient1));
        assertEquals("Размер списка ингредиентов не увеличился на 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue("Список ингредиентов не стал пустым", burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals("ingredient2 не стал первым в списке", ingredient2, burger.ingredients.get(0));
        assertEquals("ingredient1 не стал вторым в списке", ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        Mockito.when(bun.getName()).thenReturn("Разноцветная булка");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("Самый вкусный");
        Mockito.when(ingredient1.getPrice()).thenReturn(25f);
        String expectedReceipt = String.format("(==== Разноцветная булка ====)%n") +
                String.format("= sauce Самый вкусный =%n") +
                String.format("(==== Разноцветная булка ====)%n") +
                String.format("%nPrice: %f%n", 225f);
        assertEquals("Чек не совпадает с шаблоном", expectedReceipt, burger.getReceipt());
    }
}
