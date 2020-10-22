package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.model.Model;

/**
 * Lists all rooms in the address book to the user.
 */
public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "rooms";
    public static final String COMMAND_VACANT_FLAG = "vacant";
    public static final String COMMAND_ALLOCATED_FLAG = "allocated";

    public static final String MESSAGE_SUCCESS = "Listed all rooms";
    public static final String MESSAGE_VACANT_SUCCESS = "Listed all vacant rooms";
    public static final String MESSAGE_ALLOCATED_SUCCESS = "Listed all allocated rooms";

    public static final Help HELP = new Help(COMMAND_WORD, "Lists all rooms within the system.",
            "If the --" + COMMAND_VACANT_FLAG
            + " flag is specified, lists only vacant rooms i.e rooms which have no students are allocated to them. "
            + "Otherwise, if the --" + COMMAND_ALLOCATED_FLAG + " flag is specified, "
            + "lists only allocated rooms i.e rooms that have students allocated to them.\n"
            + "Parameters: [--" + COMMAND_VACANT_FLAG + " | --" + COMMAND_ALLOCATED_FLAG + "]\n"
            + "Example: " + COMMAND_WORD);

    private final ListRoomFilter filter;

    public ListRoomCommand(ListRoomFilter filter) {
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        String message = MESSAGE_SUCCESS;
        switch (filter) {
        case ALL: {
            model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
            message = MESSAGE_SUCCESS;
            break;
        }
        case VACANT: {
            model.updateFilteredRoomList(room -> !model.isAllocated(room));
            message = MESSAGE_VACANT_SUCCESS;
            break;
        }
        case ALLOCATED: {
            model.updateFilteredRoomList(model::isAllocated);
            message = MESSAGE_ALLOCATED_SUCCESS;
            break;
        }
        default:
            break;
        }
        return new ToggleCommandResult(message, TabView.ROOMS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListRoomCommand) // instanceof handles nulls
                && filter.equals(((ListRoomCommand) other).filter);
    }
}
