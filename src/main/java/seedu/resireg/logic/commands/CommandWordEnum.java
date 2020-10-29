package seedu.resireg.logic.commands;

import seedu.resireg.logic.parser.AddAliasCommandParser;
import seedu.resireg.logic.parser.AddCommandParser;
import seedu.resireg.logic.parser.AddRoomCommandParser;
import seedu.resireg.logic.parser.AllocateCommandParser;
import seedu.resireg.logic.parser.DeallocateCommandParser;
import seedu.resireg.logic.parser.DeleteAliasCommandParser;
import seedu.resireg.logic.parser.DeleteCommandParser;
import seedu.resireg.logic.parser.DeleteRoomCommandParser;
import seedu.resireg.logic.parser.EditCommandParser;
import seedu.resireg.logic.parser.FindCommandParser;
import seedu.resireg.logic.parser.ListRoomCommandParser;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ReallocateCommandParser;
import seedu.resireg.logic.parser.RestoreCommandParser;
import seedu.resireg.logic.parser.SetBinExpiryCommandParser;
import seedu.resireg.logic.parser.exceptions.EditRoomCommandParser;

/**
 * Enums representing all the commands in ResiReg. Each enum is bound to its command word, help, parser and a
 * list of AppModes that the command is available in. This enum is the single source of truth about all commands
 * in ResiReg, so developers who want to add new commands must add an enum to this class.
 */
public enum CommandWordEnum {

    // basics
    EXIT_COMMAND(ExitCommand.COMMAND_WORD, ExitCommand.HELP, args -> new ExitCommand()),
    HELP_COMMAND(HelpCommand.COMMAND_WORD, HelpCommand.HELP, HelpCommand::new),

    REDO_COMMAND(RedoCommand.COMMAND_WORD, RedoCommand.HELP, args -> new RedoCommand()),
    UNDO_COMMAND(UndoCommand.COMMAND_WORD, UndoCommand.HELP, args -> new UndoCommand()),

    ADD_ALIAS_COMMAND(AddAliasCommand.COMMAND_WORD, AddAliasCommand.HELP, new AddAliasCommandParser()::parse),
    DELETE_ALIAS_COMMAND(DeleteAliasCommand.COMMAND_WORD, DeleteAliasCommand.HELP,
            new DeleteAliasCommandParser()::parse),
    LIST_ALIAS_COMMAND(ListAliasCommand.COMMAND_WORD, ListAliasCommand.HELP, args -> new ListAliasCommand()),

    // rooms
    LIST_ROOM_COMMAND(ListRoomCommand.COMMAND_WORD, ListRoomCommand.HELP, new ListRoomCommandParser()::parse),
    EDIT_ROOM_COMMAND(EditRoomCommand.COMMAND_WORD, EditRoomCommand.HELP, new EditRoomCommandParser()::parse),
    ADD_ROOM_COMMAND(AddRoomCommand.COMMAND_WORD, AddRoomCommand.HELP, new AddRoomCommandParser()::parse),
    DELETE_ROOM_COMMAND(DeleteRoomCommand.COMMAND_WORD, DeleteRoomCommand.HELP, new DeleteRoomCommandParser()::parse),

    // misc
    TOGGLE_TAB_SPLIT_COMMAND(ToggleTabSplitCommand.COMMAND_WORD, ToggleTabSplitCommand.HELP,
        args -> new ToggleTabSplitCommand()),
    HISTORY_COMMAND(HistoryCommand.COMMAND_WORD, HistoryCommand.HELP, args -> new HistoryCommand()),
    CLEAR_COMMAND(ClearCommand.COMMAND_WORD, ClearCommand.HELP, args -> new ClearCommand()),
    ARCHIVE_COMMAND(ArchiveCommand.COMMAND_WORD, ArchiveCommand.HELP, unused -> new ArchiveCommand()),

    // bin
    RESTORE_COMMAND(RestoreCommand.COMMAND_WORD, RestoreCommand.HELP, new RestoreCommandParser()::parse),
    LIST_BIN_COMMAND(ListBinCommand.COMMAND_WORD, ListBinCommand.HELP, args -> new ListBinCommand()),
    SET_BIN_EXPIRY_COMMAND(SetBinExpiryCommand.COMMAND_WORD, SetBinExpiryCommand.HELP,
            new SetBinExpiryCommandParser()::parse),

    // students
    ADD_COMMAND(AddCommand.COMMAND_WORD, AddCommand.HELP, new AddCommandParser()::parse),
    DELETE_COMMAND(DeleteCommand.COMMAND_WORD, DeleteCommand.HELP, new DeleteCommandParser()::parse),
    EDIT_COMMAND(EditCommand.COMMAND_WORD, EditCommand.HELP, new EditCommandParser()::parse),
    FIND_COMMAND(FindCommand.COMMAND_WORD, FindCommand.HELP, new FindCommandParser()::parse),
    LIST_COMMAND(ListCommand.COMMAND_WORD, ListCommand.HELP, args -> new ListCommand()),

    // allocations
    ALLOCATE_COMMAND(AllocateCommand.COMMAND_WORD, AllocateCommand.HELP, new AllocateCommandParser()::parse),
    DEALLOCATE_COMMAND(DeallocateCommand.COMMAND_WORD, DeallocateCommand.HELP, new DeallocateCommandParser()::parse),
    REALLOCATE_COMMAND(ReallocateCommand.COMMAND_WORD, ReallocateCommand.HELP, new ReallocateCommandParser()::parse);

    private String commandWord;
    private Parser<Command> commandParser;
    private Help help;

    CommandWordEnum(String commandWord, Help help, Parser<Command> commandParser) {
        this.commandWord = commandWord;
        this.help = help;
        this.commandParser = commandParser;
    }

    public Parser<Command> getCommandParser() {
        return commandParser;
    }

    public Help getHelp() {
        return help;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String toString() {
        return commandWord;
    }

}