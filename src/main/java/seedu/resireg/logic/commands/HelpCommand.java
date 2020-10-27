package seedu.resireg.logic.commands;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.CommandMapper;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Displays help message for a command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = CommandWordEnum.HELP_COMMAND.toString();

    public static final Help HELP = new Help(COMMAND_WORD,
            "Gets help for a command or ResiReg in general.",
            "When used with the command name (eg. help exit), it displays the help message for that command."
                    + " When used without any arguments, it displays a summary of all the commands.");

    public static final String MESSAGE_UNKNOWN_COMMAND = "Cannot show help for %s: " + Messages.MESSAGE_UNKNOWN_COMMAND;

    public static final String MESSAGE_GENERAL_HELP = "Commands available:\n"
            // summary for commands available (excluding help command), in alphabetical order
            + new CommandMapper(new ArrayList<>()).getCommandWordToHelpMap().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .filter(entry -> !entry.getKey().equals(HelpCommand.COMMAND_WORD))
                    .map(entry -> entry.getValue().getSummary())
                    .collect(Collectors.joining("\n"))
            // full help message for help command
            + "\n\n" + HELP.getFullMessage() + "\n\n"
            // user guide
            + "You can also refer to our user guide at: https://ay2021s1-cs2103-t16-3.github.io/tp/UserGuide.html";

    private String input;

    /**
     * Creates a HelpCommand to display help for the specified command. If no command is given, a summary of all
     * the commands available is shown.
     *
     * @param input Command word of the command to display help for.
     */
    public HelpCommand(String input) {
        this.input = input.strip();
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        final Map<String, Help> commandWordToHelpMap =
            new CommandMapper(model.getCommandWordAliases()).getCommandWordToHelpMap();

        if (input.isBlank()) {
            return new CommandResult(MESSAGE_GENERAL_HELP);
        } else if (commandWordToHelpMap.containsKey(input)) { // print full help message for specific command
            return new CommandResult(commandWordToHelpMap.get(input).getFullMessage());
        } else {
            throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, input));
        }
    }


}
