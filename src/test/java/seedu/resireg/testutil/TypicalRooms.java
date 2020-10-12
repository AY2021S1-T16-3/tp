package seedu.resireg.testutil;

import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_RENOVATED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.resireg.model.AddressBook;
import seedu.resireg.model.room.Room;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {
    public static final Room ROOM_ONE = new RoomBuilder().withFloor("19")
            .withRoomNumber("112")
            .withRoomType("CA")
            .withTags("repaired").build();
    public static final Room ROOM_TWO = new RoomBuilder().withFloor("4")
            .withRoomNumber("102")
            .withRoomType("CN")
            .withTags("deserted").build();
    public static final Room ROOM_THREE = new RoomBuilder().withFloor("5")
            .withRoomNumber("107")
            .withRoomType("NN")
            .withTags("stuffy").build();
    public static final Room ROOM_FOUR = new RoomBuilder().withFloor("8")
            .withRoomNumber("107")
            .withRoomType("NA")
            .withTags("musical").build();
    public static final Room ROOM_FIVE = new RoomBuilder().withFloor("9")
            .withRoomNumber("103")
            .withRoomType("CN")
            .withTags("airy").build();

    // Manually added - Room's details found in {@code CommandTestUtil}
    public static final Room ROOM_A = new RoomBuilder().withFloor(VALID_FLOOR_A)
            .withRoomNumber(VALID_ROOM_NUMBER_A).withRoomType(VALID_ROOM_TYPE_A).withTags(VALID_TAG_RENOVATED).build();
    public static final Room ROOM_B = new RoomBuilder().withFloor(VALID_FLOOR_B)
            .withRoomNumber(VALID_ROOM_NUMBER_B).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_DAMAGED).build();

    private TypicalRooms() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical rooms.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Room room : getTypicalRooms()) {
            ab.addRoom(room);
        }
        return ab;
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(ROOM_ONE, ROOM_TWO, ROOM_THREE, ROOM_FOUR, ROOM_FIVE));
    }
}

