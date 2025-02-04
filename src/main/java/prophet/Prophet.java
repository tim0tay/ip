package prophet;

import prophet.command.Command;
import prophet.exception.ProphetException;
import prophet.parser.Parser;
import prophet.storage.Save;
import prophet.storage.Storage;
import prophet.ui.Ui;

import java.util.ArrayList;
public class Prophet {
    /**
     * The Prophet class is the main class that runs the chatbot.
     */

    private static final String horizontalLine = "-----------------------------------" +
            "---------------------------------------------\n";
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
        Ui ui = new Ui();
        Save.load(ui, Prophet.storage);
        Ui.greet();
        String str = ui.run();
        while (!str.equalsIgnoreCase("bye")) {
            try {
                ArrayList<Command> command = Parser.parse(ui, str);
                for (Command c : command) {
                    c.execute(ui, Prophet.storage);
                }
            } catch (ProphetException e) {
                System.out.println(e);
            }
            finally {
                str = ui.run();
            }
        }
        Ui.bye();
        Save.save(ui, Prophet.storage);
    }
}
