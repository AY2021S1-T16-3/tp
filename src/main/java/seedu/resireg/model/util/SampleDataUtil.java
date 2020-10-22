package seedu.resireg.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.resireg.model.AddressBook;
import seedu.resireg.model.ReadOnlyAddressBook;
import seedu.resireg.model.allocation.Allocation;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Faculty("FASS"), new StudentId("E0111111"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Faculty("SOC"), new StudentId("E0222222"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Faculty("DEN"), new StudentId("E0333333"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Faculty("FOS"), new StudentId("E0444444"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Faculty("BIZ"), new StudentId("E0555555"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Faculty("FASS"), new StudentId("E0666666"),
                getTagSet("colleagues"))
        };
    }

    public static Room[] getSampleRooms() {
        return new Room[] {
            new Room(new Floor("11"), new RoomNumber("108"), new RoomType("CA"),
                    getTagSet("normal")),
            new Room(new Floor("11"), new RoomNumber("104"), new RoomType("CN"),
                    getTagSet("normal")),
            new Room(new Floor("9"), new RoomNumber("102"), new RoomType("NN"),
                    getTagSet("normal")),
            new Room(new Floor("8"), new RoomNumber("107"), new RoomType("NA"),
                    getTagSet("room")),
            new Room(new Floor("7"), new RoomNumber("106"), new RoomType("CN"),
                    getTagSet("hello"))
        };
    }

    public static Allocation[] getSampleAllocations() {
        return new Allocation[] {
            new Allocation(new Floor("11"), new RoomNumber("108"), new StudentId("E0111111"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Room sampleRoom : getSampleRooms()) {
            sampleAb.addRoom(sampleRoom);
        }
        for (Allocation sampleAllocation : getSampleAllocations()) {
            sampleAb.addAllocation(sampleAllocation);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
