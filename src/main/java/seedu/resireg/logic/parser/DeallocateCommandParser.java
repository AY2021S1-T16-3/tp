package seedu.resireg.logic.parser;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.AllocateCommand;
import seedu.resireg.logic.commands.DeallocateCommand;
import seedu.resireg.logic.commands.DeleteCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

/**
 * Parses input arguments and creates a new DeallocateCommand object
 */
public class DeallocateCommandParser implements Parser<DeallocateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeallocateCommand
     * and returns an DeallocateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeallocateCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeallocateCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeallocateCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}