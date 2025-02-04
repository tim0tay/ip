package prophet.command;

import prophet.storage.Storage;
import prophet.task.ToDoTask;
import prophet.ui.Ui;

public class AddToDoCommand extends Command {
    /**
     * The AddToDoCommand class represents the command to add a to-do task.
     */
    private static final String HORIZONTAL_LINE = "-------------------------------------"
            + "-------------------------------------------\n";
    private final String taskDescription;

    /**
     * Initialises a newly created AddToDoCommand object.
     * @param command the type of command
     * @param taskDescription the description of the task
     */
    public AddToDoCommand(CommandType command, String taskDescription) {
        super(command);
        this.taskDescription = taskDescription;
    }

    /**
     * Adds a to-do task to the list of tasks.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ToDoTask newTask = new ToDoTask(this.taskDescription);
        String added = storage.addToList(newTask);
        ui.print(HORIZONTAL_LINE + added + "\n" + HORIZONTAL_LINE);
    }
}
