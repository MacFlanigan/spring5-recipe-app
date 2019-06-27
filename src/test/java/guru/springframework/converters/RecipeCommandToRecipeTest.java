package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMesureCommandToUnitOfMesure()), new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        // == given ==
        RecipeCommand command = new RecipeCommand();
        command.setId(Constants.ID);
        command.setDescription(Constants.DESCRIPTION);
        command.setPrepTime(Constants.INT_VALUE);
        command.setCookTime(Constants.INT_VALUE);
        command.setServings(Constants.INT_VALUE);
        command.setSource(Constants.SOURCE);
        command.setDirections(Constants.STRING_VALUE);
        command.setDifficulty(Difficulty.HARD);
        // --------------------------------------------
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(Constants.ID);
        notesCommand.setRecipeNotes(Constants.NOTES_DESCRIPTION);
        command.setNotes(notesCommand);
        // --------------------------------------------
        Set<CategoryCommand> categoryCommands = new HashSet<>();
        categoryCommands.add(new CategoryCommand());
        command.setCategories(categoryCommands);
        // --------------------------------------------
        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(new IngredientCommand());
        command.setIngredients(ingredientCommands);

        // == when ==
        Recipe recipe = converter.convert(command);

        // == then ==
        assertNotNull(recipe);
        assertEquals(Constants.ID, recipe.getId());
        assertEquals(Constants.DESCRIPTION, recipe.getDescription());
        assertEquals(Difficulty.HARD, recipe.getDifficulty());
        assertThat(recipe.getCategories(), hasSize(1));
        assertThat(recipe.getIngredients(), hasSize(1));
    }
}