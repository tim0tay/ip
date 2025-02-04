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
     * Prints out all current tasks.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        String list = storage.enumerateList();
        ui.print(horizontalLine + list + "\n" + horizontalLine);
    }
}
