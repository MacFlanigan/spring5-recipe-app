package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("=====> I'm in the service");
        //  Set<Recipe> recipeSet = new HashSet<>();
        // recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        // return recipeSet;

        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
