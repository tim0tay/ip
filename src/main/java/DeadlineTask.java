public class DeadlineTask extends Task {
    protected static final String type = "D | ";
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = "by: " + by;
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return DeadlineTask.type + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the deadline.
     * @return the string that represents the deadline description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + " " + this.by + "\n";
    }
}
