package seedu.resireg.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalRooms.ROOM_A;
import static seedu.resireg.testutil.TypicalRooms.ROOM_B;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.RoomBuilder;

public class RoomTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Room room = new RoomBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> room.getTags().remove(0));
    }

    @Test
    public void isSameRoom() {
        // same object, returns true
        assertTrue(ROOM_A.isSameRoom(ROOM_A));

        // null, returns false
        assertFalse(ROOM_A.isSameRoom(null));

        // different room type, same floor and room number -> returns true
        Room editedRoomA = new RoomBuilder(ROOM_A).withRoomType(VALID_ROOM_TYPE_B).build();
        assertTrue(ROOM_A.isSameRoom(editedRoomA));

        // different tags, same floor and room number -> returns true
        editedRoomA = new RoomBuilder(ROOM_A).withTags(VALID_TAG_DAMAGED).build();
        assertTrue(ROOM_A.isSameRoom(editedRoomA));

        // different floor -> returns false
        editedRoomA = new RoomBuilder(ROOM_A).withFloor(VALID_FLOOR_B).build();
        assertFalse(ROOM_A.isSameRoom(editedRoomA));

        // different room number -> returns false
        editedRoomA = new RoomBuilder(ROOM_A).withRoomNumber(VALID_ROOM_NUMBER_B).build();
        assertFalse(ROOM_A.isSameRoom(editedRoomA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Room roomACopy = new RoomBuilder(ROOM_A).build();
        assertTrue(ROOM_A.equals(roomACopy));

        // same object -> returns true
        assertTrue(ROOM_A.equals(ROOM_A));

        // null -> returns false
        assertFalse(ROOM_A.equals(null));

        // different type -> returns false
        assertFalse(ROOM_A.equals(7));

        // different room -> returns false
        assertFalse(ROOM_A.equals(ROOM_B));

        // different floor -> returns false
        Room editedRoomA = new RoomBuilder(ROOM_A).withFloor(VALID_FLOOR_B).build();
        assertFalse(ROOM_A.equals(editedRoomA));

        // different room number -> returns false
        editedRoomA = new RoomBuilder(ROOM_A).withRoomNumber(VALID_ROOM_NUMBER_B).build();
        assertFalse(ROOM_A.equals(editedRoomA));

        // different room type -> returns false
        editedRoomA = new RoomBuilder(ROOM_A).withRoomType(VALID_ROOM_TYPE_B).build();
        assertFalse(ROOM_A.equals(editedRoomA));

        // different tags -> returns false
        editedRoomA = new RoomBuilder(ROOM_A).withTags(VALID_TAG_DAMAGED).build();
        assertFalse(ROOM_A.equals(editedRoomA));
    }
}
