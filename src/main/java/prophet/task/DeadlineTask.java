package prophet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected static final String type = "D | ";
    protected LocalDate deadline;

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
        return DeadlineTask.type + super.getStatusIcon();
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
