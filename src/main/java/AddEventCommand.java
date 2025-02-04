import java.time.LocalDate;

public class AddEventCommand extends Command {
    /**
     * The AddEventCommand class represents the command to add an event task.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private final String taskDescription;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initialises a newly created AddEventCommand object.
     * @param command the type of command
     * @param taskDescription the description of the task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public AddEventCommand(CommandType command, String taskDescription, LocalDate from, LocalDate to) {
        super(command);
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the list of tasks.
     * Prints out the confirmation message or the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        EventTask newTask = new EventTask(this.taskDescription, this.from, this.to);
        String added = storage.addToList(newTask);
        ui.print(horizontalLine + added + "\n" + horizontalLine);
    }
}
