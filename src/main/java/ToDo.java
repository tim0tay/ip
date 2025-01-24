public class ToDo extends Task {
    protected static final String type = "[T]";
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return ToDo.type + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the todo.
     * @return the string that represents the todo description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + "\n";
    }
}
