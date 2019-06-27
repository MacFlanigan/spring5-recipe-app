package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMesureToUnitOfMesureCommand());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }


    @Test
    public void convert() {
        // == given ==
        Ingredient ingredient = new Ingredient();
        ingredient.setId(Constants.ID);
        ingredient.setDescription(Constants.DESCRIPTION);
        ingredient.setAmount(Constants.AMOUNT);
        UnitOfMesure unitOfMesure = new UnitOfMesure();
        unitOfMesure.setId(Constants.ID);
        unitOfMesure.setDescription(Constants.UOM_DESCRIPTION);
        ingredient.setUom(unitOfMesure);

        // == when ==
        IngredientCommand command = converter.convert(ingredient);
        // == then ==
        assertNotNull(command);
        assertEquals(Constants.ID, command.getId());
        assertEquals(Constants.DESCRIPTION, command.getDescription());
        assertEquals(Constants.AMOUNT, command.getAmount());
        assertEquals(Constants.ID, command.getUom().getId());
        assertEquals(Constants.UOM_DESCRIPTION, command.getUom().getDescription());
    }
}