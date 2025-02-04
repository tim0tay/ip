public class UnknownCommand extends Command {
    /**
     * The UnknownCommand class represents the command to handle unknown commands from the chatbot.
     */
    private static final String horizontalLine = "---------------------------------------" +
            "-----------------------------------------\n";
    private static final String message = "Part of your description is missing. Usage is as such: \n" +
            "todo description \nOR \ndeadline description /by when (format: YYYY-MM-DD) " +
            "\nOR \nevent description /from when (format: YYYY-MM-DD) /to when (format: YYYY-MM-DD)\n";

    /**
     * Initialises a newly created UnknownCommand object.
     * @param command the type of command
     */
    public UnknownCommand(CommandType command) {
        super(command);
    }

    /**
     * Executes the command to handle unknown commands.
     * Prints out the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.print(horizontalLine + UnknownCommand.message + "\n" + horizontalLine);
    }
}
