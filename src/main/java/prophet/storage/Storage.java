package prophet.storage;

import prophet.task.Task;
import prophet.todolist.TodoList;

public class Storage {
    /**
     * The Storage class represents the storage of tasks accumulated as the chatbot runs.
     */
    private final TodoList tasks;

    /**
     * Initialises a newly created Storage object that has a TodoList object.
     */
    public Storage() {
        this.tasks = new TodoList();
    }

    /**
     * Enumerates the list of tasks.
     */
    public String enumerateList() {
        return tasks.enumerateList();
    }

    /**
     * Enumerates the list of tasks for saving purposes.
     */
    public String enumerateSaveList() {
        return tasks.enumerateSaveList();
    }

    /**
     * Marks a task as done.
     * @param taskNumber the task number to be marked as done
     */
    public String markDone(int taskNumber) {
        try {
            return tasks.markDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as not done.
     * @param taskNumber the task number to be marked as not done
     */
    public String markNotDone(int taskNumber) {
        try {
            return tasks.markNotDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a to-do task to the list of tasks.
     * @param task the description of the task
     */
    public String addToList(Task task) {
        return tasks.addToList(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskNumber the task number to be deleted
     */
    public String deleteTask(int taskNumber) {
        try {
            return tasks.deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the length of the list of tasks.
     * @return the length of the list of tasks
     */
    public int getListSize() {
        return tasks.getListSize();
    }
}
