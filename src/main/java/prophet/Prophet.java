package prophet;

import java.text.NumberFormat;
import java.util.ArrayList;

import prophet.command.Command;
import prophet.exception.ProphetException;
import prophet.gui.Ui;
import prophet.parser.Parser;
import prophet.storage.Save;
import prophet.storage.Storage;

/**
 * The Prophet class is the main class that runs the chatbot.
 */

public class Prophet {
    private static Storage storage;
    private static Ui ui;

    /**
     * Initialises a newly created Prophet object.
     */
    public Prophet() {
        Prophet.storage = new Storage();
        Prophet.ui = new Ui();
        Save.load(ui, Prophet.storage);
    }

    /**
     * Returns the size of storage of tasks.
     * @return the size of storage of tasks
     */
    public static int getStorageSize() {
        return storage.getListSize();
    }

    public static void main(String[] args) {
<<<<<<< HEAD

=======
>>>>>>> master
    }

    /**
     * Generates a response for the user's chat message. Parses the input and does as user inputs,
     * returning a string that will be printed by the GUI.
     * @param input the user's chat message
     */
    public String getResponse(String input) {
        if (!input.equalsIgnoreCase("bye")) {
            try {
                ArrayList<Command> command = Parser.parse(input);
                StringBuilder response = new StringBuilder();
                for (Command c : command) {
                    response.append(c.execute(ui, Prophet.storage));
                }
                Save.save(Prophet.ui, Prophet.storage);
                return response.toString();
            } catch (ProphetException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "Please enter a valid integer!";
            }
        } else {
            Save.save(Prophet.ui, Prophet.storage);
            return Ui.greetGoodbye();
        }
    }
}
