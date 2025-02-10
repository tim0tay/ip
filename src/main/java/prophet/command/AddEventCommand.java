package prophet.command;

import prophet.task.EventTask;
import prophet.storage.Storage;
import prophet.gui.Ui;

import java.time.LocalDate;

public class AddEventCommand extends Command {
    /**
     * The AddEventCommand class represents the command to add an event task.
     */
    private final String taskDescription;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initialises a newly created AddEventCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public AddEventCommand(CommandType type, String taskDescription, LocalDate from, LocalDate to) {
        super(type);
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the list of tasks.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        EventTask newTask = new EventTask(this.taskDescription, this.from, this.to);
        String added = storage.addToList(newTask);
        return ui.print(added + "\n");
    }
}
