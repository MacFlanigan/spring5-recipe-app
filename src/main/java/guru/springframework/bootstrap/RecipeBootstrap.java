package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMesureRepository unitOfMesureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(UnitOfMesureRepository unitOfMesureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMesureRepository = unitOfMesureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // == Get UOMs ==
        Optional<UnitOfMesure> eachMesureOptional = unitOfMesureRepository.findByDescription("Each");
        if (eachMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> tablespoonMesureOptional = unitOfMesureRepository.findByDescription("Tablespoon");
        if (tablespoonMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> teaspoonMesureOptional = unitOfMesureRepository.findByDescription("Teaspoon");
        if (teaspoonMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> dashMesureOptional = unitOfMesureRepository.findByDescription("Dash");
        if (dashMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> pintMesureOptional = unitOfMesureRepository.findByDescription("Pint");
        if (pintMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> cupMesureOptional = unitOfMesureRepository.findByDescription("Cup");
        if (cupMesureOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        // == Get Optionals ==
        UnitOfMesure eachUOM = eachMesureOptional.get();
        UnitOfMesure tablespoonUOM = tablespoonMesureOptional.get();
        UnitOfMesure teaspoonUOM = teaspoonMesureOptional.get();
        UnitOfMesure dashUOM = dashMesureOptional.get();
        UnitOfMesure pintUOM = pintMesureOptional.get();
        UnitOfMesure cupUOM = cupMesureOptional.get();

        // == get categories ==
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // == Yumi Guac recipe ==
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon." +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setNotes(guacNotes);

        guacamole.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(.5), teaspoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("lemon juice", new BigDecimal(2), tablespoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("red onion", new BigDecimal(2), tablespoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("cilantro", new BigDecimal(2), tablespoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("black pepper", new BigDecimal(2), dashUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("ripe tomato", new BigDecimal(2), eachUOM, guacamole));

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        // == Add to the list ==
        recipes.add(guacamole);

        // == Yumi tacos ==
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy grilled chicken tacos");
        tacos.setCookTime(9);
        tacos.setPrepTime(20);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat." +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving." +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges." +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setNotes(tacosNotes);

        tacos.getIngredients().add(new Ingredient("Ancho chili powder", new BigDecimal(2), tablespoonUOM, tacos));
        tacos.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teaspoonUOM, tacos));
        tacos.getIngredients().add(new Ingredient("Dried cumin", new BigDecimal(1), teaspoonUOM, tacos));
        tacos.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), teaspoonUOM, tacos));
        tacos.getIngredients().add(new Ingredient("Garlic", new BigDecimal(1), eachUOM, tacos));
        tacos.getIngredients().add(new Ingredient("baby arugula", new BigDecimal(3), cupUOM, tacos));
        tacos.getIngredients().add(new Ingredient("cherry tomatoes", new BigDecimal(2), pintUOM, tacos));

        tacos.getCategories().add(americanCategory);
        tacos.getCategories().add(mexicanCategory);

        // == Add to the list ==
        recipes.add(tacos);

        return recipes;
    }
}
