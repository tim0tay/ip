package prophet.task;

public class ToDoTask extends Task {
    /**
     * The ToDoTask class represents a task that needs to be done.
     */

    protected static final String type = "T | ";

    /**
     * Initialises a newly created ToDoTask object with a description.
     * @param description the description of the task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return ToDoTask.type + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the to do.
     * @return the string that represents the to do description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + "\n";
    }
}
