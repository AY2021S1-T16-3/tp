package seedu.resireg.logic.commands;

import seedu.resireg.ui.MainWindow;

// @@author CornCobs-reused
// Reused from
// https://github.com/AY1920S2-CS2103-W15-2/
// with minor modifications
public class ToggleCommandResult extends CommandResult {
    private final TabView tabView;

    /**
     * Constructs a {@code ToggleCommandResult} with the specified {@code feedbackToUser},
     * that will toggle the UI to the given TabView.
     */
    public ToggleCommandResult(String feedbackToUser, TabView tabView) {
        super(feedbackToUser);
        this.tabView = tabView;
    }

    @Override
    public void displayResult(MainWindow mainWindow) {
        mainWindow.handleToggle(tabView);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && other instanceof ToggleCommandResult
                && tabView == ((ToggleCommandResult) other).tabView;
    }

}

