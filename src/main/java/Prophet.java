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
                String list = tasks.enumerateList();
                System.out.println(horizontalLine + list + "\n" + horizontalLine);
            } else {
                String added = tasks.addToList(str);
                System.out.println(horizontalLine + added + "\n" + horizontalLine);
            }
            str = sc.nextLine();
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
    }
}
