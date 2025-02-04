package prophet.ui;

import java.util.Scanner;

public class Ui {
    /**
     * The Ui class represents the user interface of the chatbot.
     */
    private static final String horizontalLine = "----------------------------------------" +
            "----------------------------------------\n";
    private static final String message = "Part of your description is missing. Usage is as such: \n" +
            "todo description \nOR \ndeadline description /by when (format: YYYY-MM-DD) " +
            "\nOR \nevent description /from when (format: YYYY-MM-DD) /to when (format: YYYY-MM-DD)\n";
    private Scanner scanner;

    /**
     * Initialises a newly created Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message of the chatbot.
     */
    public static void greet() {
        System.out.println(horizontalLine
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks. What would you like to remember?\n"
                + horizontalLine);
    }

    /**
     * Prints the farewell message of the chatbot.
     */
    public static void bye() {
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
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
