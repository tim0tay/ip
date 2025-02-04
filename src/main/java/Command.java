public abstract class Command {
    /**
     * The Command class represents commands that can be run by the chatbot.
     * To implement this class, extend this class and override the execute method.
     */
    private CommandType command;

    public Command(CommandType command) {
        this.command = command;
    }

    /**
     * Executes the command specific to the object.
     */
    public abstract void execute(Storage storage) throws InvalidTaskNumberException, NoDescriptionException;
}
