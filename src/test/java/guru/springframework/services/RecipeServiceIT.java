package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.constants.Constants;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        // == given ==
        Recipe recipe = recipeRepository.findAll().iterator().next();
        RecipeCommand command = recipeToRecipeCommand.convert(recipe);

        // == when ==
        command.setDescription(Constants.DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);

        // == then ==
        assertEquals(Constants.DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(recipe.getId(), savedRecipeCommand.getId());
        assertEquals(recipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
