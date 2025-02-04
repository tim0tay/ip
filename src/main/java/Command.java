public abstract class Command {
    /**
     * The Command class represents commands that can be run by the chatbot.
     * To implement this class, extend this class and override the execute method.
     */
    private final CommandType command;

    public Command(CommandType command) {
        this.command = command;
    }

    /**
     * Executes the command specific to the object.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    public abstract void execute(Ui ui, Storage storage) throws InvalidTaskNumberException, NoDescriptionException;
}
