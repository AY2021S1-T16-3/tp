package seedu.resireg.model.allocation;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;

/**
 * Represents an allocation association between a Student and a Room.
 * Guarantees: Details are present and non-null, field values are validated, immutable.
 */
public class Allocation {

    // Data fields
    private final Floor floor;
    private final RoomNumber roomNumber;
    private final StudentId studentId;

    /**
     * Every field must be present and not null.
     */
    public Allocation(Floor floor, RoomNumber roomNumber, StudentId studentId) {
        requireAllNonNull(floor, roomNumber, studentId);
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.studentId = studentId;
    }

    public Floor getFloor() {
        return floor;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns true if the allocation is related to {@code room}.
     */
    public boolean isRelatedTo(Room room) {
        return room.getFloor().equals(getFloor())
                && room.getRoomNumber().equals(getRoomNumber());
    }

    /**
     * Returns true if the allocation is related to {@code student}.
     */
    public boolean isRelatedTo(Student student) {
        return student.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both allocations have the same room identification (floor and roomNumber).
     * This defines a weaker notion of equality between two allocations
     */
    public boolean isSameAllocation(Allocation otherAllocation) {
        if (otherAllocation == this) {
            return true;
        }
        return otherAllocation != null
                && ((otherAllocation.getFloor().equals(getFloor())
                    && otherAllocation.getRoomNumber().equals(getRoomNumber()))
                        || otherAllocation.getStudentId().equals(getStudentId()));
    }

    /**
     * Returns true if both allocations have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Allocation)) {
            return false;
        }

        Allocation otherAllocation = (Allocation) other;
        return otherAllocation.getFloor().equals(getFloor())
                && otherAllocation.getRoomNumber().equals(getRoomNumber())
                && otherAllocation.getStudentId().equals(getStudentId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(floor, roomNumber, studentId);
    }

    @Override
    public String toString() {
        return getFloor()
                + " Floor: "
                + getRoomNumber()
                + " Room Number: "
                + getStudentId()
                + " Student ID: ";
    }
}
