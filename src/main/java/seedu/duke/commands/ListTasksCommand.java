package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class ListTasksCommand extends Command {

    //@@author aliciatay-zls
    /**
     * Prints tasks in selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ui.printAllTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
