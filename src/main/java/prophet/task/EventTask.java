package prophet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task that spans a period of time.
 */
public class EventTask extends Task {
    private static final String TYPE = "E | ";
    private final LocalDate from;
    private final LocalDate to;

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
     * Checks if the task is due between the event's duration.
     * @param date the date to check against
     */
    @Override
    public boolean isDueOn(LocalDate date) {
        return (this.from.isBefore(date) && this.to.isAfter(date)) || this.from.equals(date) || this.to.equals(date);
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return EventTask.TYPE + super.getStatusIcon();
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
