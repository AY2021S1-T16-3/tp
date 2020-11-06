package seedu.resireg.logic.commands;

import java.io.File;
import java.util.logging.Logger;

import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

public class TutorialCommand extends Command {
    public static final String COMMAND_WORD = "tutorial";
    public static final String MESSAGE_SUCCESS = "Dame da ne, dame yo, dame na no yo";
    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "Sorry, we were unable to open the tutorial video.";
    public static final String TUTORIAL_VIDEO_PATH = "/media/tutorial.mp4";

    private static final Logger logger = LogsCenter.getLogger(TutorialCommand.class);

    public static final Help HELP = new Help(COMMAND_WORD, "Watch a tutorial video on ResiReg usage.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        logger.info("Executing tutorial command...");

        try {
            var path = new File(getClass().getResource(TUTORIAL_VIDEO_PATH).toURI()).getCanonicalPath();
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.startsWith("Windows")) {
                Runtime.getRuntime().exec("powershell.exe -Command Invoke-Item '" + path + "'");
            } else if (operatingSystem.startsWith("darwin") || operatingSystem.startsWith("mac")) {
                Runtime.getRuntime().exec("open " + path);
            } else { // assume linux, and xdg-open present
                Runtime.getRuntime().exec("xdg-open " + path);
            }
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }
    }
}
