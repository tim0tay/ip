public class DeleteTaskCommand extends Command {
    /**
     * The DeleteTaskCommand class represents the command to delete a task from the chatbot.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private final int index;

    /**
     * Initialises a newly created DeleteTaskCommand object.
     * @param command the type of command
     * @param index the description of the task.
     */
    public DeleteTaskCommand(CommandType command, int index) {
        super(command);
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
        ui.print(horizontalLine + deleted + "\n" + horizontalLine);
    }
}
