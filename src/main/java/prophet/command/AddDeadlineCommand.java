package prophet.command;

import prophet.task.DeadlineTask;
import prophet.storage.Storage;
import prophet.ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    /**
     * The AddDeadlineCommand class represents the command to add a deadline task.
     */
    private static final String HORIZONTAL_LINE = "-------------------------------------------"
            + "-------------------------------------\n";
    private final String taskDescription;
    private final LocalDate deadline;

    /**
     * Initialises a newly created AddDeadlineCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     * @param deadline the deadline of the task
     */
    public AddDeadlineCommand(CommandType type, String taskDescription, LocalDate deadline) {
        super(type);
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Adds a deadline task to the list of tasks.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        DeadlineTask newTask = new DeadlineTask(this.taskDescription, this.deadline);
        String added = storage.addToList(newTask);
        ui.print(HORIZONTAL_LINE + added + "\n" + HORIZONTAL_LINE);
    }
}
