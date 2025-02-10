package prophet.command;

import prophet.storage.Storage;
import prophet.task.ToDoTask;
import prophet.gui.Ui;

public class AddToDoCommand extends Command {
    /**
     * The AddToDoCommand class represents the command to add a to-do task.
     */
    private final String taskDescription;

    /**
     * Initialises a newly created AddToDoCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     */
    public AddToDoCommand(CommandType type, String taskDescription) {
        super(type);
        this.taskDescription = taskDescription;
    }

    /**
     * Adds a to-do task to the list of tasks.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        ToDoTask newTask = new ToDoTask(this.taskDescription);
        String added = storage.addToList(newTask);
        return ui.print(added + "\n");
    }
}
