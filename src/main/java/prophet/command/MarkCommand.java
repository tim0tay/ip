package prophet.command;

import prophet.storage.Storage;
import prophet.ui.Ui;

public class MarkCommand extends Command {
    /**
     *  The MarkCommand class represents the command to mark a task as done.
     */
    private static final String HORIZONTAL_LINE = "------------------------------"
            + "--------------------------------------------------\n";
    private final int index;

    /**
     * Initialises a newly created MarkCommand object.
     * @param command the type of command
     * @param index the index of the task to be marked as done
     */
    public MarkCommand(CommandType command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Marks a specific task as done.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        String marked = storage.setDone(index);
        ui.print(HORIZONTAL_LINE + marked + "\n" + HORIZONTAL_LINE);
    }
}
