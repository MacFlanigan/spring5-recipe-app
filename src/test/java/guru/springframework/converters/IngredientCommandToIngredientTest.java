package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMesureCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMesureCommandToUnitOfMesure());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        // == given ==
        IngredientCommand command = new IngredientCommand();
        command.setId(Constants.ID);
        command.setDescription(Constants.DESCRIPTION);
        command.setAmount(Constants.AMOUNT);
        UnitOfMesureCommand unitOfMesureCommand = new UnitOfMesureCommand();
        unitOfMesureCommand.setId(Constants.ID);
        unitOfMesureCommand.setDescription(Constants.UOM_DESCRIPTION);
        command.setUom(unitOfMesureCommand);

        // == when ==
        Ingredient ingredient = converter.convert(command);
        // == then ==
        assertNotNull(ingredient);
        assertEquals(Constants.ID, ingredient.getId());
        assertEquals(Constants.DESCRIPTION, ingredient.getDescription());
        assertEquals(Constants.AMOUNT, ingredient.getAmount());
        assertEquals(Constants.ID, ingredient.getUom().getId());
        assertEquals(Constants.UOM_DESCRIPTION, ingredient.getUom().getDescription());
    }
}