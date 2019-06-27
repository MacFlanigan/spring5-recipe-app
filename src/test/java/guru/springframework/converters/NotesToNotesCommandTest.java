package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        // == given ==
        Notes notes = new Notes();
        notes.setId(Constants.ID);
        notes.setRecipeNotes(Constants.DESCRIPTION);

        // == when ==
        NotesCommand command = converter.convert(notes);

        // == then ==
        assertNotNull(command);
        assertEquals(Constants.ID, command.getId());
        assertEquals(Constants.DESCRIPTION, command.getRecipeNotes());
    }
}