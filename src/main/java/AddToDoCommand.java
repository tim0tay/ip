public class AddToDoCommand extends Command {
    /**
     * The AddToDoCommand class represents the command to add a to-do task.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
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
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Storage storage) {
        ToDoTask newTask = new ToDoTask(this.taskDescription);
        String added = storage.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }
}
