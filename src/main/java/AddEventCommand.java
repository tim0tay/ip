public class AddEventCommand extends Command {
    /**
     * The AddEventCommand class represents the command to add an event task.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private final String taskDescription;
    private final String from;
    private final String to;

    /**
     * Initialises a newly created AddEventCommand object.
     * @param command the type of command
     * @param taskDescription the description of the task
     */
    public AddEventCommand(CommandType command, String taskDescription, String from, String to) {
        super(command);
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the list of tasks.
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Storage storage) {
        EventTask newTask = new EventTask(this.taskDescription, this.from, this.to);
        String added = storage.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }
}
