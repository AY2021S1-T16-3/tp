package seedu.resireg.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.allocation.UniqueAllocationList;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.UniqueRoomList;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final UniqueRoomList rooms;
    private final UniqueAllocationList allocations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        rooms = new UniqueRoomList();
        allocations = new UniqueAllocationList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students and Rooms in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms
     */
    public void setAllocations(List<Allocation> allocations) {
        this.allocations.setAllocations(allocations);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setRooms(newData.getRoomList());
        setAllocations(newData.getAllocationList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Returns true if an allocation relating to {@code student} exists in the address book.
     */
    public boolean isAllocated(Student student) {
        requireNonNull(student);
        return allocations.contains(student);
    }

    /**
     * Returns true if an allocation relating to {@code room} exists in the address book.
     */
    public boolean isAllocated(Room room) {
        requireNonNull(room);
        return allocations.contains(room);
    }


    //// room-level operations

    /**
     * Adds a room to the address book.
     * The room must not already exist in the address book.
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }

    /**
     * Returns true if a room with the same identity as {@code room} exists in the address book.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return rooms.contains(room);
    }

    /**
     * Replaces the given room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedStudent} must not be the same as another existing room
     * in the address book.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireNonNull(editedRoom);
        rooms.setRoom(target, editedRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRoom(Room key) {
        rooms.remove(key);
    }

    //// allocation-level operations

    /**
     * Adds an allocation to the address book.
     * The allocation must not already exist in the address book.
     */
    public void addAllocation(Allocation allocation) {
        requireNonNull(allocation);
        allocations.add(allocation);
    }

    /**
     * Returns true if an allocation with the same identity as {@code allocation} exists in the address book.
     */
    public boolean hasAllocation(Allocation allocation) {
        requireNonNull(allocation);
        return allocations.contains(allocation);
    }

    /**
     * Replaces the given allocation {@code target} in the list with {@code editedAllocation}.
     * {@code target} must exist in the address book.
     * The allocation identity of {@code editedALlocation} must not be the same as another existing allocation
     * in the address book.
     */
    public void setAllocation(Allocation target, Allocation editedAllocation) {
        requireNonNull(editedAllocation);
        allocations.setAllocation(target, editedAllocation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAllocation(Allocation key) {
        allocations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Allocation> getAllocationList() {
        return allocations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students)
                && rooms.equals(((AddressBook) other).rooms)
                && allocations.equals(((AddressBook) other).allocations));
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, rooms, allocations);
    }
}
