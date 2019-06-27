package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // == given ==
        CategoryCommand command = new CategoryCommand();
        command.setId(Constants.ID);
        command.setDescription(Constants.DESCRIPTION);

        // == when ==
        Category category = converter.convert(command);

        // == then ==
        assertNotNull(category);
        assertEquals(Constants.ID, category.getId());
        assertEquals(Constants.DESCRIPTION, category.getDescription());
    }
}