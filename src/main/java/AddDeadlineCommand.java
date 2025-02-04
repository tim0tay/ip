public class AddDeadlineCommand extends Command {
    /**
     * The AddDeadlineCommand class represents the command to add a deadline task.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private final String taskDescription;
    private final String deadline;

    /**
     * Initialises a newly created AddDeadlineCommand object.
     * @param command the type of command
     * @param taskDescription the description of the task
     * @param deadline the deadline of the task
     */
    public AddDeadlineCommand(CommandType command, String taskDescription, String deadline) {
        super(command);
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Adds a deadline task to the list of tasks.
     * @param storage the storage of tasks
     */
    @Override
    public void execute(Storage storage) {
        DeadlineTask newTask = new DeadlineTask(this.taskDescription, this.deadline);
        String added = storage.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }
}
