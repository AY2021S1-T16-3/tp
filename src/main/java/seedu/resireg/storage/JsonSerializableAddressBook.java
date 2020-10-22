package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.AddressBook;
import seedu.resireg.model.ReadOnlyAddressBook;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_ROOM = "Rooms list contains duplicate room(s).";
    public static final String MESSAGE_DUPLICATE_ALLOCATION = "Rooms list contains duplicate allocation(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();
    private final List<JsonAdaptedAllocation> allocations = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("rooms") List<JsonAdaptedRoom> rooms,
                                       @JsonProperty("allocations") List<JsonAdaptedAllocation> allocations) {
        this.students.addAll(students);
        this.rooms.addAll(rooms);
        this.allocations.addAll(allocations);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        rooms.addAll(source.getRoomList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
        allocations.addAll(
                source.getAllocationList().stream().map(JsonAdaptedAllocation::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            if (addressBook.hasRoom(room)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROOM);
            }
            addressBook.addRoom(room);
        }
        for (JsonAdaptedAllocation jsonAdaptedAllocation : allocations) {
            Allocation allocation = jsonAdaptedAllocation.toModelType();
            if (addressBook.hasAllocation(allocation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALLOCATION);
            }
            addressBook.addAllocation(allocation);
        }
        return addressBook;
    }

}
