import java.util.ArrayList;
import java.util.Scanner;
public class Prophet {
    /**
     * The Prophet class is the main class that runs the chatbot.
     */

    private static final String horizontalLine = "--------------------------------------------------------------------------------\n";
    private static Storage storage;

    /**
     * Returns the size of storage of tasks.
     * @return the size of storage of tasks
     */
    public static int getStorageSize() {
        return storage.getListSize();
    }

    public static void main(String[] args) {
        Prophet.storage = new Storage();
        Save.load(Prophet.storage);
        System.out.println(horizontalLine
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks. What would you like to remember?\n"
                + horizontalLine);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equalsIgnoreCase("bye")) {
            try {
                ArrayList<Command> command = Parser.parse(str);
                for (Command c : command) {
                    c.execute(Prophet.storage);
                }
            } catch (ProphetException e) {
                System.out.println(e);
            } finally {
                str = sc.nextLine();
            }
        }
        Save.save(Prophet.storage);
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
    }
}
