package prophet.exception;

public class UnrecognisedCommandException extends ProphetException {
    protected String message;

    public UnrecognisedCommandException() {
        this.message = "I'm sorry, I don't know what you mean! I can do the following: \n" +
                "1. Add tasks (keywords are todo, deadline or event)\n" +
                "2. Mark tasks done or not done (keywords are mark or unmark)\n" +
                "3. List out your tasks (keyword is list)\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the unrecognised command exception
     */
    public String toString() {
        return this.message;
    }
}
