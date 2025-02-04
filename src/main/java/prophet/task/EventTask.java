package prophet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    /**
     * The EventTask class represents a task that spans a period of time.
     */

    protected static final String type = "E | ";
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initialises a newly created EventTask object with a description and a time period.
     * @param description the description of the task
     * @param from the start of the time period
     * @param to the end of the time period
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return EventTask.type + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the event.
     * @return the string that represents the event description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription()
                + " from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "\n";
    }
}
