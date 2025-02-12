package prophet.command;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The UnknownCommand class represents the command to handle unknown commands from the chatbot.
 */
public class UnknownCommand extends Command {
    private static final String MESSAGE = "Part of your description is missing. Usage is as such: \n"
            + "todo description \nOR \ndeadline description /by when (format: YYYY-MM-DD) "
            + "\nOR \nevent description /from when (format: YYYY-MM-DD) /to when (format: YYYY-MM-DD)\n";

    /**
     * Initialises a newly created UnknownCommand object.
     * @param type the type of command
     */
    public UnknownCommand(CommandType type) {
        super(type);
    }

    /**
     * Executes the command to handle unknown commands.
     * Prints out the error message.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
    * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.print(UnknownCommand.MESSAGE + "\n");
    }
}
