package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.constants.Constants;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        // == given ==
        NotesCommand command = new NotesCommand();
        command.setId(Constants.ID);
        command.setRecipeNotes(Constants.DESCRIPTION);

        // == when ==
        Notes notes = converter.convert(command);

        // == then ==
        assertNotNull(notes);
        assertEquals(Constants.ID, notes.getId());
        assertEquals(Constants.DESCRIPTION, notes.getRecipeNotes());
    }
}