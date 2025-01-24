import java.util.Scanner;
public class Prophet {
    protected TodoList tasks;
    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private static final String usage = "Please type a proper format..!\n";
    public Prophet() {
        this.tasks = new TodoList();
    }
    public void inputInterpretator(String str) throws UnrecognisedCommandException {
        try {
            if (str.equalsIgnoreCase("list")) {
                this.printList();
            } else if (str.toLowerCase().startsWith("mark")) {
                this.markDone(str);
            } else if (str.toLowerCase().startsWith("unmark")) {
                this.markNotDone(str);
            } else if (str.toLowerCase().startsWith("todo")) {
                this.addToDo(str);
            } else if (str.toLowerCase().startsWith("deadline")) {
                this.addDeadline(str);
            } else if (str.toLowerCase().startsWith("event")) {
                this.addEvent(str);
            } else if (str.toLowerCase().startsWith("delete")) {
                this.deleteTask(str);
            } else {
                throw new UnrecognisedCommandException();
            }
        } catch (ProphetException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Prints a string that lists all tasks.
     */
    public void printList() {
        String list = tasks.enumerateList();
        System.out.println(horizontalLine + list + "\n" + horizontalLine);
    }

    /**
     * Mark a specific task as done.
     * @param str string that represents the task number, to be converted into an integer.
     *            The string must contain the exact integer alone
     */
    public void markDone(String str) throws InvalidTaskNumberException {
        try {
            String[] mark = str.split("mark ", 2);
            if (mark.length < 2 || mark[1].isEmpty()) {
                throw new InvalidTaskNumberException();
            }
            String marked = this.tasks.markDone(Integer.parseInt(mark[1]) - 1);
            System.out.println(horizontalLine + marked + "\n" + horizontalLine);
        } catch (NumberFormatException e) {
            System.out.println("The number you keyed in was not an integer! Try again.");
        }
    }

    /**
     * Mark a specific task as not done.
     * @param str string that represents the task number, to be converted into an integer.
     *            The string must contain the exact integer alone
     */
    public void markNotDone(String str) throws InvalidTaskNumberException {
        try {
            String[] unmark = str.split("unmark ", 2);
            if (unmark.length < 2 || unmark[1].isEmpty()) {
                throw new InvalidTaskNumberException();
            }
            String marked = this.tasks.markNotDone(Integer.parseInt(unmark[1]) - 1);
            System.out.println(horizontalLine + marked + "\n" + horizontalLine);
        } catch (NumberFormatException e) {
            System.out.println("The number you keyed in was not an integer! Try again.");
        }
    }

    /**
     * Add a to-do task to the tasklist.
     * @param str string that represents the task description.
     *            The string must contain a description only in the format: todo description
     */
    public void addToDo(String str) throws NoDescriptionException {
        // splits accordingly to get description
        String[] description = str.split("todo ", 2);
        if (description.length < 2 || description[1].trim().isEmpty()) {
            throw new NoDescriptionException();
        }
        ToDo newTask = new ToDo(description[1]);
        String added = tasks.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }

    /**
     * Add a deadline task to the tasklist.
     * @param str string that represents the task description.
     *            The string must contain a description and a deadline in the format: deadline description /by when.
     */
    public void addDeadline(String str) throws NoDescriptionException {
        // splits according to first space to get description and deadline
        String[] description = str.split(" ", 2);
        if (description.length < 2 || description[1].trim().isEmpty() || description[1].trim().startsWith("/by")) {
            throw new NoDescriptionException();
        }
        // splits description and deadline up
        String[] remainingParts = description[1].split("/by",2);
        if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
            throw new NoDescriptionException();
        }
        Deadline newTask = new Deadline(remainingParts[0], remainingParts[1]);
        String added = tasks.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }

    /**
     * Add an event task to the tasklist.
     * @param str string that represents the task description.
     *            The string must contain a description and a timeline in the format: deadline description /from when /to when.
     */
    public void addEvent(String str) throws NoDescriptionException {
        // splits according to first space to get description and deadline
        String[] description = str.split(" ", 2);
        if (description.length < 2 || description[1].trim().isEmpty() || description[1].trim().startsWith("/from")) {
            throw new NoDescriptionException();
        }
        // splits description and timeline up
        String[] remainingParts = description[1].split("/from ",2);
        if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
            throw new NoDescriptionException();
        }
        // splits timeline up
        String[] timeline = remainingParts[1].split("/to ",2);
        if (timeline.length < 2 || timeline[1].trim().isEmpty()) {
            throw new NoDescriptionException();
        }
        Event newTask = new Event(remainingParts[0], timeline[0], timeline[1]);
        String added = tasks.addToList(newTask);
        System.out.println(horizontalLine + added + "\n" + horizontalLine);
    }

    /**
     * Mark a specific task as done.
     * @param str string that represents the task number, to be converted into an integer.
     *            The string must contain the exact integer alone
     */
    public void deleteTask(String str) throws InvalidTaskNumberException {
        try {
            String[] delete = str.split("delete ", 2);
            if (delete.length < 2 || delete[1].isEmpty()) {
                throw new InvalidTaskNumberException();
            }
            String deleted = this.tasks.deleteTask(Integer.parseInt(delete[1]) - 1);
            System.out.println(horizontalLine + deleted + "\n" + horizontalLine);
        } catch (NumberFormatException e) {
            System.out.println("The number you keyed in was not an integer! Try again.");
        }
    }

    public static void main(String[] args) {
        Prophet prophet = new Prophet();
        System.out.println(horizontalLine
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks. What would you like to remember?\n"
                + horizontalLine);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equalsIgnoreCase("bye")) {
            try {
                prophet.inputInterpretator(str);
            } catch (ProphetException e) {
                System.out.println(e.toString());
            } finally {
                str = sc.nextLine();
            }
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
    }
}
