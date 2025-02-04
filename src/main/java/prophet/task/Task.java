package prophet.task;

public class Task {
    protected String description;
    protected boolean isDone;

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
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Toggles the task's status to not done.
     */
    public void markNotDone() {
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
