package prophet.task;

public class Task {
    /**
     * The Task class represents a task with a description and a status.
     */

    protected String description;
    protected boolean isDone;

    /**
     * Initialises a newly created Task object with a description and a status.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string that visually indicates the status of the task.
     * @return the string that represents the task status
     */
    public String getStatusIcon() {
        return this.isDone ? "[X] " : "[ ] ";
    }

    /**
     * Returns a string of the task's description.
     * @return the string that represents the task description
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Toggles the task's status to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Toggles the task's status to not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's description.
     * @return the string that represents the task description
     */
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
