public class Event extends Task {
    protected static final String type = "[E]";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = "(from: " + from + " ";
        this.to = "to: " + to + ")";
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return Event.type + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the event.
     * @return the string that represents the event description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + this.from + this.to + "\n";
    }
}
