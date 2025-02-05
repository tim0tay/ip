package prophet.command;

import prophet.storage.Storage;
import prophet.ui.Ui;

public class FindTaskCommand extends Command {
    /**
     * The FindTaskCommand class represents a command to find tasks.
     */

    private String keyword;

    /**
     * Initialises a newly created FindTaskCommand object with a keyword.
     * @param type the type of command
     * @param keyword the keyword to search for
     */
    public FindTaskCommand(CommandType type, String keyword) {
        super(type);
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks.
     * @param ui the user interface
     * @param storage the storage of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.print(storage.findTasks(this.keyword));
    }
}
