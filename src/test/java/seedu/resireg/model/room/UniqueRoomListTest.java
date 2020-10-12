package seedu.resireg.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalRooms.ROOM_A;
import static seedu.resireg.testutil.TypicalRooms.ROOM_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.room.exceptions.DuplicateRoomException;
import seedu.resireg.model.room.exceptions.RoomNotFoundException;
import seedu.resireg.testutil.RoomBuilder;

public class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void contains_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.contains(null));
    }

    @Test
    public void contains_roomNotInList_returnsFalse() {
        assertFalse(uniqueRoomList.contains(ROOM_A));
    }

    @Test
    public void contains_roomInList_returnsTrue() {
        uniqueRoomList.add(ROOM_A);
        assertTrue(uniqueRoomList.contains(ROOM_A));
    }

    @Test
    public void contains_roomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoomList.add(ROOM_A);
        Room editedRoomA = new RoomBuilder(ROOM_A).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_DAMAGED)
                .build();
        assertTrue(uniqueRoomList.contains(editedRoomA));
    }

    @Test
    public void add_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.add(null));
    }

    @Test
    public void add_duplicateRoom_throwsDuplicateRoomException() {
        uniqueRoomList.add(ROOM_A);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.add(ROOM_A));
    }

    @Test
    public void setRoom_nullTargetRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRoom(null, ROOM_A));
    }

    @Test
    public void setRoom_nullEditedRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRoom(ROOM_A, null));
    }

    @Test
    public void setRoom_targetRoomNotInList_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () -> uniqueRoomList.setRoom(ROOM_A, ROOM_A));
    }

    @Test
    public void setRoom_editedRoomIsSameRoom_success() {
        uniqueRoomList.add(ROOM_A);
        uniqueRoomList.setRoom(ROOM_A, ROOM_A);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_A);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasSameIdentity_success() {
        uniqueRoomList.add(ROOM_A);
        Room editedRoomA = new RoomBuilder(ROOM_A).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_DAMAGED)
                .build();
        uniqueRoomList.setRoom(ROOM_A, editedRoomA);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(editedRoomA);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasDifferentIdentity_success() {
        uniqueRoomList.add(ROOM_A);
        uniqueRoomList.setRoom(ROOM_A, ROOM_B);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_B);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasNonUniqueIdentity_throwsDuplicateRoomException() {
        uniqueRoomList.add(ROOM_A);
        uniqueRoomList.add(ROOM_B);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.setRoom(ROOM_A, ROOM_B));
    }

    @Test
    public void remove_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.remove(null));
    }

    @Test
    public void remove_roomDoesNotExist_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () -> uniqueRoomList.remove(ROOM_A));
    }

    @Test
    public void remove_existingRoom_removesRoom() {
        uniqueRoomList.add(ROOM_A);
        uniqueRoomList.remove(ROOM_A);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_nullUniqueRoomList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((UniqueRoomList) null));
    }

    @Test
    public void setRooms_uniqueRoomList_replacesOwnListWithProvidedUniqueRoomList() {
        uniqueRoomList.add(ROOM_A);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_B);
        uniqueRoomList.setRooms(expectedUniqueRoomList);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((List<Room>) null));
    }

    @Test
    public void setRooms_list_replacesOwnListWithProvidedList() {
        uniqueRoomList.add(ROOM_A);
        List<Room> roomList = Collections.singletonList(ROOM_B);
        uniqueRoomList.setRooms(roomList);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_B);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_listWithDuplicateRooms_throwsDuplicateRoomException() {
        List<Room> listWithDuplicateRooms = Arrays.asList(ROOM_A, ROOM_A);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.setRooms(listWithDuplicateRooms));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRoomList.asUnmodifiableObservableList().remove(0));
    }
}
