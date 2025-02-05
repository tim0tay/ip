package prophet.command;

import prophet.exception.InvalidTaskNumberException;
import prophet.storage.Storage;
import prophet.ui.Ui;

public class DeleteTaskCommand extends Command {
    /**
     * The DeleteTaskCommand class represents the command to delete a task from the chatbot.
     */
    private static final String HORIZONTAL_LINE = "------------------------------------"
            + "--------------------------------------------\n";
    private final int index;

    /**
     * Initialises a newly created DeleteTaskCommand object.
     * @param type the type of command
     * @param index the description of the task.
     */
    public DeleteTaskCommand(CommandType type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Deletes a specific task.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) throws InvalidTaskNumberException {
        String deleted = storage.deleteTask(this.index);
        ui.print(HORIZONTAL_LINE + deleted + "\n" + HORIZONTAL_LINE);
    }
}
