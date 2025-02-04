package prophet.ui;

import java.util.Scanner;

public class Ui {
    /**
     * The Ui class represents the user interface of the chatbot.
     */
    private static final String HORIZONTAL_LINE = "----------------------------------------"
            + "----------------------------------------\n";
    private final Scanner scanner;

    /**
     * Initialises a newly created Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message of the chatbot.
     */
    public static void greetHello() {
        System.out.println(HORIZONTAL_LINE
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks."
                + "I can do the following: \n"
                + "1. Add tasks (keywords are todo, deadline or event)\n"
                + "2. Mark tasks done or not done (keywords are mark or unmark)\n"
                + "3. List out your tasks (keyword is list)\n"
                + "4. Delete tasks (keyword is delete)\n"
                + "What would you like to do?\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints the farewell message of the chatbot.
     */
    public static void greetGoodbye() {
        System.out.println(HORIZONTAL_LINE + "Bye! Hope to see you again!\n" + HORIZONTAL_LINE);
    }

    /**
     * Runs the ui of the chatbot.
     * @return the string inputted by the user
     */
    public String run() {
        return scanner.nextLine();
    }

    /**
     * Prints the argument supplied.
     * @param message the message to be printed
     */
    public void print(String message) {
        System.out.println(message);
    }
}
