public class ProphetException extends Exception {
    protected String message;

    public ProphetException() {
        this.message = "Oops! Hit a wall somewhere...\n";
    }

    /**
     * Returns a string representation of the exception.
     * @return the string that represents the Prophet's exception
     */
    public String toString() {
        return this.message;
    }
}
