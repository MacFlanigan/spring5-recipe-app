package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        // == given ==
        Category category = new Category();
        category.setId(Constants.ID);
        category.setDescription(Constants.DESCRIPTION);

        // == when ==
        CategoryCommand command = converter.convert(category);

        // == then ==
        assertNotNull(command);
        assertEquals(Constants.ID, command.getId());
        assertEquals(Constants.DESCRIPTION, command.getDescription());
    }
}