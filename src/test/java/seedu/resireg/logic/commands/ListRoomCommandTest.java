package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertToggleCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalRooms.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;

public class ListRoomCommandTest {

    private static final boolean SHOULD_DISPLAY = true;
    private static final boolean SHOULD_NOT_DISPLAY = false;

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertToggleCommandSuccess(
                new ListRoomCommand(SHOULD_NOT_DISPLAY),
                model, ListRoomCommand.MESSAGE_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listIsFiltered_showsEverything() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        assertToggleCommandSuccess(
                new ListRoomCommand(SHOULD_DISPLAY),
                model,
                ListRoomCommand.MESSAGE_VACANT_SUCCESS, expectedModel, TabView.ROOMS);
    }
}