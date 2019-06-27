package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMesureToUnitOfMesureCommand()), new NotesToNotesCommand());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }


    @Test
    public void convert() {
        // == given ==
        Recipe recipe = new Recipe();
        recipe.setId(Constants.ID);
        recipe.setDescription(Constants.DESCRIPTION);
        recipe.setPrepTime(Constants.INT_VALUE);
        recipe.setCookTime(Constants.INT_VALUE);
        recipe.setServings(Constants.INT_VALUE);
        recipe.setSource(Constants.SOURCE);
        recipe.setDirections(Constants.STRING_VALUE);
        recipe.setDifficulty(Difficulty.HARD);
        // --------------------------------------------
        Notes notes = new Notes();
        notes.setId(Constants.ID);
        notes.setRecipeNotes(Constants.NOTES_DESCRIPTION);
        recipe.setNotes(notes);
        // --------------------------------------------
        Set<Category> categories = new HashSet<>();
        categories.add(new Category());
        recipe.setCategories(categories);
        // --------------------------------------------
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient());
        recipe.setIngredients(ingredients);

        // == when ==
        RecipeCommand command = converter.convert(recipe);

        // == then ==
        assertNotNull(command);
        assertEquals(Constants.ID, command.getId());
        assertEquals(Constants.DESCRIPTION, command.getDescription());
        assertEquals(Difficulty.HARD, command.getDifficulty());
        assertThat(command.getCategories(), hasSize(1));
        assertThat(command.getIngredients(), hasSize(1));
    }
}