package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertToggleCommandSuccess;
import static seedu.resireg.testutil.TypicalRooms.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;

public class ListRoomCommandTest {

    private static final ListRoomFilter DISPLAY_ALL = ListRoomFilter.ALL;
    private static final ListRoomFilter DISPLAY_VACANT = ListRoomFilter.VACANT;
    private static final ListRoomFilter DISPLAY_ALLOCATED = ListRoomFilter.ALLOCATED;

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
                new ListRoomCommand(DISPLAY_ALL),
                model, ListRoomCommand.MESSAGE_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterIsVacant_showsOnlyVacantRooms() {
        expectedModel.updateFilteredRoomList(room -> !model.isAllocated(room));
        assertToggleCommandSuccess(
                new ListRoomCommand(DISPLAY_VACANT),
                model,
                ListRoomCommand.MESSAGE_VACANT_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterIsAllocated_showsOnlyAllocatedRooms() {
        expectedModel.updateFilteredRoomList(room -> model.isAllocated(room));
        var cmd = new ListRoomCommand(DISPLAY_ALLOCATED);
        assertToggleCommandSuccess(
                cmd,
                model,
                ListRoomCommand.MESSAGE_ALLOCATED_SUCCESS, expectedModel, TabView.ROOMS);
    }
}
