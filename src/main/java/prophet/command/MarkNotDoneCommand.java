package prophet.command;

import prophet.storage.Storage;
import prophet.ui.Ui;

public class MarkNotDoneCommand extends Command {
    /**
     * The MarkNotDoneCommand class represents the command to mark a task as not done.
     */
    private static final String HORIZONTAL_LINE = "--------------------------------"
            + "------------------------------------------------\n";
    private final int index;

    /**
     * Initialises a newly created MarkNotDoneCommand object.
     * @param type the type of command
     * @param index the description of the task
     */
    public MarkNotDoneCommand(CommandType type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Marks a specific task as not done.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        String marked = storage.setNotDone(index);
        ui.print(HORIZONTAL_LINE + marked + "\n" + HORIZONTAL_LINE);
    }
}
