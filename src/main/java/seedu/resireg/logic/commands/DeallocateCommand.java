package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.student.Student;


/**
 * Deallocates a room to a student to the address book.
 */
public class DeallocateCommand extends Command {

    public static final String COMMAND_WORD = "deallocate";

    public static final Help HELP = new Help(COMMAND_WORD, "Deallocates a student from a room.",
            "Parameters: " + PREFIX_STUDENT_INDEX + "STUDENT INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1");

    public static final String MESSAGE_SUCCESS = "Room %1$s deallocated for %2$s.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not registered in ResiReg";
    public static final String MESSAGE_STUDENT_NOT_ALLOCATED = "This student has not been allocated a room.";

    private final Index studentIndex;

    /**
     * @param studentIndex of the student in the filtered student list to deallocate a room from
     * Creates an DeallocateCommand to deallocate the specified {@code Student}.
     */
    public DeallocateCommand(Index studentIndex) {
        requireNonNull(studentIndex);
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownListStudent = model.getFilteredStudentList();
        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();

        if (studentIndex.getZeroBased() >= lastShownListStudent.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToDeallocate = lastShownListStudent.get(studentIndex.getZeroBased());

        Allocation toDeallocate = null;
        for (Allocation allocation : lastShownListAllocation) {
            if (studentToDeallocate.getStudentId().equals(allocation.getStudentId())) {
                toDeallocate = allocation;
            }
        }

        if (!model.hasStudent(studentToDeallocate)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        } else if (toDeallocate == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_ALLOCATED);
        }

        model.removeAllocation(toDeallocate);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        model.updateFilteredAllocationList(Model.PREDICATE_SHOW_ALL_ALLOCATIONS);

        model.saveStateResiReg();

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                toDeallocate.getFloor().toString() + ':' + toDeallocate.getRoomNumber(),
                studentToDeallocate.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeallocateCommand // instanceof handles nulls
                && studentIndex.equals(((DeallocateCommand) other).studentIndex));
    }
}
