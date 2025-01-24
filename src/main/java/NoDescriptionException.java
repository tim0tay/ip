public class NoDescriptionException extends ProphetException {
    protected String message;

    public NoDescriptionException() {
        this.message = "Part of your description is missing. Usage is as such: \n" +
                "todo description \nOR \ndeadline description /by when \nOR \nevent description /from when /to when\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the no description exception
     */
    public String toString() {
        return this.message;
    }
}
