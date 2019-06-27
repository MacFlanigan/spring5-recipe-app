package guru.springframework.converters;

import guru.springframework.commands.UnitOfMesureCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMesureToUnitOfMesureCommandTest {

    private UnitOfMesureToUnitOfMesureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMesureToUnitOfMesureCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMesure()));
    }

    @Test
    public void convert() {
        // == given ==
        UnitOfMesure uom = new UnitOfMesure();
        uom.setId(Constants.ID);
        uom.setDescription(Constants.DESCRIPTION);

        // == when ==
        UnitOfMesureCommand command = converter.convert(uom);

        // == then ==
        assertNotNull(command);
        assertEquals(Constants.ID, command.getId());
        assertEquals(Constants.DESCRIPTION, command.getDescription());
    }
}