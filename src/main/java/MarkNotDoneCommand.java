public class MarkNotDoneCommand extends Command {
    /**
     * The MarkNotDoneCommand class represents the command to mark a task as not done.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private final int index;

    /**
     * Initialises a newly created MarkNotDoneCommand object.
     * @param command the type of command
     * @param index the description of the task
     */
    public MarkNotDoneCommand(CommandType command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Marks a specific task as not done.
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Storage storage) {
        String marked = storage.markNotDone(index);
        System.out.println(horizontalLine + marked + "\n" + horizontalLine);
    }
}
