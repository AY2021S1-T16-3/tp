package seedu.resireg.logic;

import java.util.Set;

import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

/**
 * Contains functionality for "copy-constructing" {@code Student} and {@code Room}
 * objects to deal with persistence of student and room allocation statuses that
 * inhibits undo and redo functionality.
 */
public class CreateEditCopy {
    /**
     * Creates a new student instance with the same information
     * as the student it is copying from.
     *
     * @param studentToCopy student to copy from
     * @return a new student instance with the same fields
     * as the student to copy from
     */
    public static Student createCopiedStudent(Student studentToCopy) {
        assert studentToCopy != null;

        Name copiedName = studentToCopy.getName();
        Phone copiedPhone = studentToCopy.getPhone();
        Email copiedEmail = studentToCopy.getEmail();
        Faculty copiedFaculty = studentToCopy.getFaculty();
        StudentId copiedStudentId = studentToCopy.getStudentId();
        Set<Tag> copiedTags = studentToCopy.getTags();

        return new Student(copiedName, copiedPhone,
                copiedEmail, copiedFaculty, copiedStudentId, copiedTags);
    }

    /**
     * Creates a new room instance with the same information
     * as the room it is copying from.
     *
     * @param roomToCopy the room to copy from
     * @return a new room instance with the same fields
     * as the room to copy from
     */
    public static Room createCopiedRoom(Room roomToCopy) {
        assert roomToCopy != null;

        Floor copiedFloor = roomToCopy.getFloor();
        RoomNumber copiedRoomNumber = roomToCopy.getRoomNumber();
        RoomType copiedRoomType = roomToCopy.getRoomType();
        Set<Tag> copiedTags = roomToCopy.getTags();

        return new Room(copiedFloor, copiedRoomNumber, copiedRoomType, copiedTags);
    }
}
