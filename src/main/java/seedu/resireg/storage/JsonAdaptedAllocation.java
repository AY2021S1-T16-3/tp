package seedu.resireg.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.student.StudentId;

/**
 * Jackson-friendly version of {@link Allocation}.
 */
class JsonAdaptedAllocation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Allocation's %s field is missing!";

    private final String floor;
    private final String roomNumber;
    private final String studentId;

    /**
     * Constructs a {@code JsonAdaptedAllocation} with the given allocation details.
     */
    @JsonCreator
    public JsonAdaptedAllocation(@JsonProperty("floor") String floor,
                                 @JsonProperty("roomNumber") String roomNumber,
                                 @JsonProperty("studentId") String studentId) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.studentId = studentId;
    }

    /**
     * Converts a given {@code Allocation} into this class for Jackson use.
     */
    public JsonAdaptedAllocation(Allocation source) {
        floor = source.getFloor().value;
        roomNumber = source.getRoomNumber().value;
        studentId = source.getStudentId().value;
    }

    /**
     * Converts this Jackson-friendly adapted allocation object into the model's {@code Allocation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allocation.
     */
    public Allocation toModelType() throws IllegalValueException {
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
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        return new Allocation(modelFloor, modelRoomNumber, modelStudentId);
    }
}

