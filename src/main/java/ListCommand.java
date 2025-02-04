public class ListCommand extends Command {
    /**
     * The ListCommand class represents a command to list all tasks in the chatbot.
     */
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";

    /**
     * Initialises a newly created ListCommand object.
     * @param command the type of command
     */
    public ListCommand(CommandType command) {
        super(command);
    }

    /**
     * Lists out all current tasks.
     * @param storage the storage of tasks
     */
    @Override
    public void execute(Storage storage) {
        String list = storage.enumerateList();
        System.out.println(horizontalLine + list + "\n" + horizontalLine);
    }
}
