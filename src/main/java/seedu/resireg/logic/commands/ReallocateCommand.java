package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;


/**
 * Adds a student to the address book.
 */
public class ReallocateCommand extends Command {

    public static final String COMMAND_WORD = "reallocate";
    public static final Help HELP = new Help(COMMAND_WORD, "Reallocates a student to a room.",
            "Parameters: " + PREFIX_STUDENT_INDEX + "STUDENT INDEX " + PREFIX_ROOM_INDEX + "ROOM INDEX\n"
                    + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1 " + PREFIX_ROOM_INDEX + "1");

    public static final String MESSAGE_SUCCESS = "Student %1$s has been reallocated to Room %2$s.";
    public static final String MESSAGE_ROOM_NOT_FOUND = "This room does not exist in ResiReg";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not registered in ResiReg";
    public static final String MESSAGE_STUDENT_NOT_ALLOCATED =
            "This student has not been allocated a room. Please use allocate instead.";
    public static final String MESSAGE_ROOM_ALREADY_ALLOCATED = "This room has already been allocated to a student.";

    private final Index studentIndex;
    private final Index roomIndex;

    /**
     * @param studentIndex of the student in the filtered student list to allocate
     * @param roomIndex of the room in the filtered room list to be allocated to
     * Creates an ReallocateCommand to allocate the specified {@code Student} to {@code Room}
     */
    public ReallocateCommand(Index studentIndex, Index roomIndex) {
        requireNonNull(studentIndex);
        requireNonNull(roomIndex);

        this.studentIndex = studentIndex;
        this.roomIndex = roomIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownListStudent = model.getFilteredStudentList();
        List<Room> lastShownListRoom = model.getFilteredRoomList();
        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();

        if (studentIndex.getZeroBased() >= lastShownListStudent.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else if (roomIndex.getZeroBased() >= lastShownListRoom.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Student studentToReallocate = lastShownListStudent.get(studentIndex.getZeroBased());
        Room roomToReallocate = lastShownListRoom.get(roomIndex.getZeroBased());

        Allocation toReallocate = null;
        for (Allocation allocation : lastShownListAllocation) {
            if (studentToReallocate.getStudentId().equals(allocation.getStudentId())) {
                toReallocate = allocation;
            }
        }

        if (!model.hasStudent(studentToReallocate)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        } else if (!model.hasRoom(roomToReallocate)) {
            throw new CommandException(MESSAGE_ROOM_NOT_FOUND);
        } else if (toReallocate == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_ALLOCATED);
        }

        Allocation editedAllocation = new Allocation(roomToReallocate.getFloor(), roomToReallocate.getRoomNumber(),
                        studentToReallocate.getStudentId());
        model.setAllocation(toReallocate, editedAllocation);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        model.updateFilteredAllocationList(Model.PREDICATE_SHOW_ALL_ALLOCATIONS);

        model.saveStateResiReg();

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToReallocate.getName().fullName,
            roomToReallocate.getRoomLabel()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReallocateCommand // instanceof handles nulls
                && studentIndex.equals(((ReallocateCommand) other).studentIndex)
                && roomIndex.equals(((ReallocateCommand) other).roomIndex));
    }
}
