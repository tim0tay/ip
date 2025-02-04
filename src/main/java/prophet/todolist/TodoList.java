package prophet.todolist;

import prophet.task.Task;

import java.util.ArrayList;
import java.util.List;
public class TodoList {
    /**
     * The TodoList class represents a list of tasks that the user has to do.
     */

    private final List<Task> list;

    /**
     * Initialises a newly created TodoList object with an empty list.
     */
    public TodoList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the input task to the list of things to do.
     * @param newTask the task description
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
     * Returns a string of the whole to-do list and its status for saving purposes.
     * @return the string that represents the to-do list
     */
    public String enumerateSaveList() {
        StringBuilder result = new StringBuilder();
        for (Task task : this.list) {
            result.append(task.toString());
        }
        return result.toString();
    }

    /**
     * Marks a specified task done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked done
     * @throws IndexOutOfBoundsException if the task number is invalid
     * @return the string that confirms successful marking of the task
     */
    public String markDone(int taskNumber) throws IndexOutOfBoundsException {
        try {
            this.list.get(taskNumber).markDone();
            StringBuilder result = new StringBuilder("Done! Good job.\n");
            result.append(this.list.get(taskNumber).getStatusIcon())
                    .append(this.list.get(taskNumber).getTaskDescription()).append("\n");
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a specified task not done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked not done
     * @throws IndexOutOfBoundsException if the task number is invalid
     * @return the string that confirms successful marking of the task
     */
    public String markNotDone(int taskNumber) throws IndexOutOfBoundsException {
        try {
            this.list.get(taskNumber).markNotDone();
            StringBuilder result = new StringBuilder("Marked not done! Jiayous...\n");
            result.append(this.list.get(taskNumber).getStatusIcon())
                    .append(this.list.get(taskNumber).getTaskDescription()).append("\n");
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Removes the input task from the list of things to do.
     * @param  taskNumber the task description
     * @throws IndexOutOfBoundsException if the task number is invalid
     * @return the confirmation string that indicates successful removal
     */
    public String deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        try {
            Task task = this.list.get(taskNumber);
            this.list.remove(taskNumber);
            StringBuilder result = new StringBuilder("The following task was removed: \n");
            result.append(task.getStatusIcon())
                    .append(task.getTaskDescription()).append("\n");
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the size of the list.
     * @return the size of the list
     */
    public int getListSize() {
        return this.list.size();
    }
}
