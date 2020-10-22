package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Room}.
 */
class JsonAdaptedRoom {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Room's %s field is missing!";

    private final String floor;
    private final String roomNumber;
    private final String roomType;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRoom} with the given room details.
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("floor") String floor, @JsonProperty("roomNumber") String number,
                           @JsonProperty("roomType") String roomType,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.floor = floor;
        this.roomNumber = number;
        this.roomType = roomType;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Room} into this class for Jackson use.
     */
    public JsonAdaptedRoom(Room source) {
        floor = source.getFloor().value;
        roomNumber = source.getRoomNumber().value;
        roomType = source.getRoomType().name;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted room object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room.
     */
    public Room toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            roomTags.add(tag.toModelType());
        }

        if (floor == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Floor.class.getSimpleName()));
        }
        if (!Floor.isValidFloor(floor)) {
            throw new IllegalValueException(Floor.MESSAGE_CONSTRAINTS);
        }
        final Floor modelFloor = new Floor(floor);

        if (roomNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Number.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelNumber = new RoomNumber(roomNumber);

        if (roomType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RoomType.class.getSimpleName()));
        }
        if (!RoomType.isValidRoomType(roomType)) {
            throw new IllegalValueException(RoomType.MESSAGE_CONSTRAINTS);
        }
        final RoomType modelRoomType = new RoomType(roomType);

        final Set<Tag> modelTags = new HashSet<>(roomTags);

        return new Room(modelFloor, modelNumber, modelRoomType, modelTags);
    }
}

