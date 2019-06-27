package guru.springframework.converters;

import guru.springframework.commands.UnitOfMesureCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMesureCommandToUnitOfMesureTest {

    private UnitOfMesureCommandToUnitOfMesure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMesureCommandToUnitOfMesure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMesureCommand()));
    }

    @Test
    public void convert() {
        // == given ==
        UnitOfMesureCommand command = new UnitOfMesureCommand();
        command.setId(Constants.ID);
        command.setDescription(Constants.DESCRIPTION);

        // == when ==
        UnitOfMesure uom = converter.convert(command);

        // == then ==
        assertNotNull(uom);
        assertEquals(Constants.ID, uom.getId());
        assertEquals(Constants.DESCRIPTION, uom.getDescription());
    }
}