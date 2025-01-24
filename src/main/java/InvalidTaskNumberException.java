public class InvalidTaskNumberException extends ProphetException {
    protected String message;

    public InvalidTaskNumberException() {
        this.message = "Whoops! Invalid number. Please key in a valid integer\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the Prophet's exception
     */
    public String toString() {
        return this.message;
    }
}
