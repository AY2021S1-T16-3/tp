package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;


/**
 * Adds a student to the address book.
 */
public class AllocateCommand extends Command {

    public static final String COMMAND_WORD = "allocate";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allocates a student to a room. "
            + "Parameters: "
            + PREFIX_STUDENT_INDEX + "STUDENT INDEX "
            + PREFIX_ROOM_INDEX + "ROOM INDEX"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_INDEX + "1 "
            + PREFIX_ROOM_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Room allocated to %1$s: %2$s";
    public static final String MESSAGE_ROOM_NOT_FOUND = "This room does not exist in ResiReg";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not registered in ResiReg";
    public static final String MESSAGE_STUDENT_ALREADY_ALLOCATED = "This student has already been allocated a room.";

    private final Index studentIndex;
    private final Index roomIndex;

    /**
     * @param studentIndex of the student in the filtered student list to allocate
     * @param roomIndex of the room in the filtered room list to be allocated to
     * Creates an AllocateCommand to allocate the specified {@code Student} to {@code Room}
     */
    public AllocateCommand(Index studentIndex, Index roomIndex) {
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

        if (studentIndex.getZeroBased() >= lastShownListStudent.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else if (roomIndex.getZeroBased() >= lastShownListRoom.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Student studentToAllocate = lastShownListStudent.get(studentIndex.getZeroBased());
        Room roomToAllocate = lastShownListRoom.get(roomIndex.getZeroBased());

        if (!model.hasStudent(studentToAllocate)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        } else if (!model.hasRoom(roomToAllocate)) {
            throw new CommandException(MESSAGE_ROOM_NOT_FOUND);
        } else if (studentToAllocate.hasRoom()) {
            throw new CommandException(MESSAGE_STUDENT_ALREADY_ALLOCATED);
        }

        studentToAllocate.setRoom(roomToAllocate);
        roomToAllocate.setStudent(studentToAllocate);

        model.setStudent(studentToAllocate, studentToAllocate);
        model.setRoom(roomToAllocate, roomToAllocate);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAllocate, roomToAllocate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllocateCommand // instanceof handles nulls
                && studentIndex.equals(((AllocateCommand) other).studentIndex)
                && roomIndex.equals(((AllocateCommand) other).roomIndex));
    }
}
