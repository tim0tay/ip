import java.util.ArrayList;
import java.util.List;
public class TodoList {
    private final List<Task> list;

    public TodoList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the input task to the list of things to do.
     * @param  newTask the task description
     * @return the confirmation string that indicates successful addition
     */
    public String addToList(Task newTask) {
        if (newTask == null) {
            return "Please.. don't make me work harder than I have to!\n";
        }
        this.list.add(newTask);
        return "Task added to list: " + newTask.toString();
    }

    /**
     * Returns a string of the whole to-do list and its status.
     * @return the string that represents the to-do list
     */
    public String enumerateList() {
        StringBuilder result = new StringBuilder("Let's see what you have on your plate:\n");
        for (int i = 0; i < this.list.size(); i++) {
            result.append(i+1).append(". ").append(this.list.get(i).toString());
        }
        if (this.list.isEmpty()) {
            result.append("So gewd ah.. nothing to do!\n");
        } else if (this.list.size() > 4) {
            result.append("Seems like you have quite a few things to do.. go easy on yourself!\n");
        }
        return result.toString();
    }
    /**
     * Marks a specified task done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked done
     * @return the string that confirms successful marking of the task
     */
    public String markDone(Integer taskNumber) {
        if (taskNumber == null || taskNumber >= this.list.size() || taskNumber < 0) {
            return "This task number can't be found! Please enter a valid task number.\n";
        }
        this.list.get(taskNumber).markDone();
        StringBuilder result = new StringBuilder("Done! Good job.\n");
        result.append(this.list.get(taskNumber).getStatusIcon())
                .append(this.list.get(taskNumber).getTaskDescription()).append("\n");
        return result.toString();
    }

    /**
     * Marks a specified task not done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked not done
     * @return the string that confirms successful marking of the task
     */
    public String markNotDone(Integer taskNumber) {
        if (taskNumber >= this.list.size() || taskNumber < 0) {
            return "This task number can't be found! Please enter a valid task number.\n";
        }
        this.list.get(taskNumber).markNotDone();
        StringBuilder result = new StringBuilder("Marked not done! Jiayous...\n");
        result.append(this.list.get(taskNumber).getStatusIcon())
                .append(this.list.get(taskNumber).getTaskDescription()).append("\n");
        return result.toString();
    }
}
