import java.util.Scanner;
public class Prophet {
    public static void main(String[] args) {
        String horizontalLine = "--------------------------------------------------------------------------------\n";
        TodoList tasks = new TodoList();
        System.out.println(horizontalLine
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks. What would you like to remember?\n"
                + horizontalLine);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                // list out the task list
                String list = tasks.enumerateList();
                System.out.println(horizontalLine + list + "\n" + horizontalLine);
            } else if (str.toLowerCase().startsWith("mark")) {
                // mark a task as done
                String marked = tasks.markDone(Integer.parseInt(str.substring(5)) - 1);
                System.out.println(horizontalLine + marked + "\n" + horizontalLine);
            } else if (str.toLowerCase().startsWith("unmark")) {
                // mark a task as not done
                String unmarked = tasks.markNotDone(Integer.parseInt(str.substring(7)) - 1);
                System.out.println(horizontalLine + unmarked + "\n" + horizontalLine);
            } else if (str.toLowerCase().startsWith("todo")) {
                // splits accordingly to get description
                String[] description = str.split("todo ", 2);
                if (description.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                ToDo newTask = new ToDo(description[1]);
                String added = tasks.addToList(newTask);
                System.out.println(horizontalLine + added + "\n" + horizontalLine);
            } else if (str.toLowerCase().startsWith("deadline")) {
                // splits according to first space to get description and deadline
                String[] description = str.split(" ", 2);
                if (description.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                // splits description and deadline up
                String[] remainingParts = description[1].split("/by",2);
                if (remainingParts.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                Deadline newTask = new Deadline(remainingParts[0], remainingParts[1]);
                String added = tasks.addToList(newTask);
                System.out.println(horizontalLine + added + "\n" + horizontalLine);
            } else if (str.toLowerCase().startsWith("event")) {
                // splits according to first space to get description and deadline
                String[] description = str.split(" ", 2);
                if (description.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                // splits description and timeline up
                String[] remainingParts = description[1].split("/from ",2);
                if (remainingParts.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                // splits timeline up
                String[] timeline = remainingParts[1].split("/to ",2);
                if (timeline.length < 2) {
                    System.out.println("Please type a proper format..!\n");
                    break;
                }
                Event newTask = new Event(remainingParts[0], timeline[0], timeline[1]);
                String added = tasks.addToList(newTask);
                System.out.println(horizontalLine + added + "\n" + horizontalLine);
            } else {
                System.out.println("Please enter a valid task in its format!\n");
            }
            str = sc.nextLine();
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
    }
}
