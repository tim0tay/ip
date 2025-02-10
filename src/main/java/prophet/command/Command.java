package prophet.command;

import prophet.exception.InvalidTaskNumberException;
import prophet.exception.NoDescriptionException;
import prophet.storage.Storage;
import prophet.gui.Ui;

public abstract class Command {
    /**
     * The Command class represents commands that can be run by the chatbot.
     * To implement this class, extend this class and override the execute method.
     */
    private final CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes the command specific to the object.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    public abstract String execute(Ui ui, Storage storage) throws InvalidTaskNumberException, NoDescriptionException;
}
