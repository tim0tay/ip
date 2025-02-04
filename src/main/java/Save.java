import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    /**
     * The Save class helps to save to and retrieve tasks from a file.
     */
    private static final String FILE_PATH = "data";
    private static final String SAVE_PATH = "data/tasks.txt";

    /**
     * Saves the tasks to a file.
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    public static void save(Storage storage) {
        try {
            File filePath = new File(FILE_PATH);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            File saveFile = new File(SAVE_PATH);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            FileWriter writer = new FileWriter(SAVE_PATH);
            writer.write(storage.enumerateSaveList());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from a file into the storage of tasks.
     */
    public static void load(Storage storage) {
        try {
            File saveFile = new File(SAVE_PATH);
            if (!saveFile.exists()) {
                return;
            }
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                ArrayList<Command> loadedCommands = Parser.parse(task);
                for (Command command : loadedCommands) {
                    command.execute(storage);
                }
            }
        } catch (IOException | InvalidTaskNumberException | NoDescriptionException e) {
            System.out.println("An error occurred while loading the tasks: " + e.getMessage());
        }
    }
}
