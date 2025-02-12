package prophet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    protected static final String TYPE = "D | ";
    protected LocalDate deadline;

    /**
     * Initialises a newly created DeadlineTask object with a description and a deadline.
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return DeadlineTask.TYPE + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the deadline.
     * @return the string that represents the deadline description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + " by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "\n";
    }
}
