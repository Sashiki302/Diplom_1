
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
    private Ingredient ingredientOne;

    @Mock
    private Ingredient ingredientTwo;

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
    public void testAddIngredientSize() {
        burger.addIngredient(ingredientOne);
        assertEquals("Размер списка ингредиентов не увеличился на 1", 1, burger.ingredients.size());
    }
    @Test
    public void testAddIngredientOne() {
        burger.addIngredient(ingredientOne);
        assertEquals("ingredientOne не добавлен в список бургера", ingredientOne, burger.ingredients.get(0));
    }
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientOne);
        burger.removeIngredient(0);
        assertTrue("Список ингредиентов не стал пустым", burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredientTwo() {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.moveIngredient(0, 1);
        assertEquals("ingredientTwo не стал первым в списке", ingredientTwo, burger.ingredients.get(0));
    }
    @Test
    public void testMoveIngredientOne() {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.moveIngredient(0, 1);
        assertEquals("ingredientOne не стал вторым в списке", ingredientOne, burger.ingredients.get(1));
    }
    @Test
    public void testGetReceiptIngredientName() {
        Mockito.when(bun.getName()).thenReturn("Разноцветная булка");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredientOne.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientOne.getName()).thenReturn("Самый вкусный");
        Mockito.when(ingredientOne.getPrice()).thenReturn(25f);
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        String receipt = burger.getReceipt();
        assertTrue("Название ингредиента в чеке не совпадает с шаблоном", receipt.contains("Самый вкусный"));
    }
    @Test
    public void testGetReceiptBunName() {
        Mockito.when(bun.getName()).thenReturn("Разноцветная булка");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue("Название булки в чеке не совпадает с шаблоном", receipt.contains("Разноцветная булка"));
    }
    @Test
    public void testGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("Разноцветная булка");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue("Итоговая стоимость в чеке не совпадает с шаблоном", receipt.contains("Price: 200"));
    }
}