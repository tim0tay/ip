package prophet.parser;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import prophet.Prophet;
import prophet.command.AddDeadlineCommand;
import prophet.command.AddEventCommand;
import prophet.command.AddToDoCommand;
import prophet.command.Command;
import prophet.command.CommandType;
import prophet.command.DeleteTaskCommand;
import prophet.command.FindTaskCommand;
import prophet.command.ListCommand;
import prophet.command.MarkCommand;
import prophet.command.MarkNotDoneCommand;
import prophet.command.UnknownCommand;
import prophet.exception.InvalidTaskNumberException;
import prophet.exception.NoDescriptionException;
import prophet.gui.Ui;

/**
 * The Parser class takes in user input and interprets it to perform the necessary actions.
 */
public class Parser {

    /**
     * Interprets command-line inputs and returns the command type according to keywords.
     * @param str the string representing the input
     * @return CommandType the type of command given in the input
     */
    public static ArrayList<Command> parse(String str)
            throws InvalidTaskNumberException, NoDescriptionException, NumberFormatException {

        String firstWord = Parser.separateStringByKeyword(str, " ", false)[0].toLowerCase();
        ArrayList<Command> commands = new ArrayList<>();

        switch (firstWord) {
        case "list":
            return Parser.addListCommand(commands);
        case "mark":
            return Parser.addMarkCommand(commands, str);
        case "unmark":
            return Parser.addMarkNotDoneCommand(commands, str);
        case "todo":
            return Parser.addToDoCommand(commands, str);
        case "t":
            return Parser.loadToDoCommandFromStorage(commands, str);
        case "deadline":
            return Parser.addDeadlineCommand(commands, str);
        case "d":
            return Parser.loadDeadlineCommandFromStorage(commands, str);
        case "event":
            return Parser.addEventCommand(commands, str);
        case "e":
            return Parser.loadEventCommandFromStorage(commands, str);
        case "delete":
           return Parser.addDeleteCommand(commands, str);
        case "find":
            return Parser.addFindCommand(commands, str);
        default:
            commands.add(new UnknownCommand(CommandType.UNKNOWN));
            return commands;
        }
    }

    /**
     * Separates a string by a keyword.
     * @param str the string to be separated
     * @param keyword the keyword to separate the string by
     * @param doChecks whether to do checks for the separated string
     * @return the separated string
     * @throws NoDescriptionException
     */
    public static String[] separateStringByKeyword(String str, String keyword, boolean doChecks)
            throws NoDescriptionException{
        String[] splitString = str.split(keyword, 2);
        if (doChecks) {
            if (splitString.length < 2 || splitString[1].trim().isEmpty()) {
                throw new NoDescriptionException();
            }
        }
        return splitString;
    }

    /**
     * Adds a ListCommand to the list of commands.
     * @param commands the command list to be run
     * @return the list of commands
     */
    public static ArrayList<Command> addListCommand(ArrayList<Command> commands) {
        commands.add(new ListCommand(CommandType.LIST));
        return commands;
    }

    /**
     * Adds a MarkCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws InvalidTaskNumberException
     */
    public static ArrayList<Command> addMarkCommand(ArrayList<Command> commands, String description)
            throws InvalidTaskNumberException, NumberFormatException {
        try {
            String[] mark = Parser.separateStringByKeyword(description, "mark ", true);
            int index = Integer.parseInt(mark[1]) - 1;
            commands.add(new MarkCommand(CommandType.MARK, index));
            return commands;
        } catch (NoDescriptionException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Adds a MarkNotDoneCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws InvalidTaskNumberException
     */
    public static ArrayList<Command> addMarkNotDoneCommand(ArrayList<Command> commands, String description)
            throws InvalidTaskNumberException, NumberFormatException {
        try {
            String[] mark = Parser.separateStringByKeyword(description, "unmark ", true);
            int index = Integer.parseInt(mark[1]) - 1;
            commands.add(new MarkNotDoneCommand(CommandType.UNMARK, index));
            return commands;
        } catch (NoDescriptionException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Adds an AddToDoCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> addToDoCommand(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] todo = Parser.separateStringByKeyword(description, "todo ", true);
            commands.add(new AddToDoCommand(CommandType.TODO, todo[1].trim()));
            return commands;
        } catch (NoDescriptionException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Loads a ToDoCommand from storage.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> loadToDoCommandFromStorage(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] todo = Parser.separateStringByKeyword(description, "T \\| ", true);
            String status = todo[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(todo[1], "]", true);
            commands.add(new AddToDoCommand(CommandType.TODO, statusAndDescription[1].trim()));
            if (status.equals("[X]")) {
                commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
            }
            return commands;
        } catch (NoDescriptionException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Adds an AddDeadlineCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> addDeadlineCommand(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] deadline = Parser.separateStringByKeyword(description, "deadline ", true);
            String[] todoAndDeadline = Parser.separateStringByKeyword(deadline[1], "/by", true);

            // formatting the date so that it is readable
            LocalDate deadlineDate = LocalDate.parse(todoAndDeadline[1].trim());

            commands.add(new AddDeadlineCommand(CommandType.DEADLINE, todoAndDeadline[0].trim(), deadlineDate));
            return commands;
        } catch (NoDescriptionException | DateTimeParseException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Loads a DeadlineCommand from storage.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> loadDeadlineCommandFromStorage(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] deadline = Parser.separateStringByKeyword(description, "D \\| ", true);
            String status = deadline[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(deadline[1], "]", true);
            String[] todoAndDeadline = Parser.separateStringByKeyword(
                    statusAndDescription[1], "by: ", true);

            // formatting the date so that it is readable
            LocalDate deadlineDate = LocalDate.parse(
                    todoAndDeadline[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));

            commands.add(new AddDeadlineCommand(CommandType.DEADLINE, todoAndDeadline[0].trim(), deadlineDate));
            if (status.equals("[X]")) {
                commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
            }
            return commands;
        } catch (NoDescriptionException | DateTimeParseException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Adds an AddEventCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> addEventCommand(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] event = Parser.separateStringByKeyword(description, "event ", true);
            String[] remainingParts = Parser.separateStringByKeyword(event[1], "/from ", true);
            String[] timeline = Parser.separateStringByKeyword(remainingParts[1], "/to ", true);

            // formatting the date so that it is readable
            LocalDate from = LocalDate.parse(timeline[0].trim());
            LocalDate to = LocalDate.parse(timeline[1].trim());

            commands.add(new AddEventCommand(
                    CommandType.EVENT, remainingParts[0].trim(), from, to));
            return commands;
        } catch (NoDescriptionException | DateTimeParseException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Loads an EventCommand from storage.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     * @throws NoDescriptionException
     */
    public static ArrayList<Command> loadEventCommandFromStorage(ArrayList<Command> commands, String description)
            throws NoDescriptionException {
        try {
            String[] event = Parser.separateStringByKeyword(description, "E \\| ", true);
            String status = event[1].substring(0, 3);
            String[] statusAndDescription = Parser.separateStringByKeyword(event[1], "]", true);
            String[] remainingParts = Parser.separateStringByKeyword(
                    statusAndDescription[1], "from: ", true);
            String[] timeline = Parser.separateStringByKeyword(remainingParts[1], "to: ", true);

            // formatting the date
            LocalDate from = LocalDate.parse(timeline[0].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
            LocalDate to = LocalDate.parse(timeline[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));

            commands.add(new AddEventCommand(
                    CommandType.EVENT, remainingParts[0].trim(), from, to));
            if (status.equals("[X]")) {
                commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
            }
            return commands;
        } catch (NoDescriptionException | DateTimeParseException e) {
            throw new NoDescriptionException();
        }
    }

    /**
     * Adds a DeleteTaskCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     */
    public static ArrayList<Command> addDeleteCommand(ArrayList<Command> commands, String description)
            throws NumberFormatException {
        try {
            String[] delete = Parser.separateStringByKeyword(description, "delete ", true);
            int index = Integer.parseInt(delete[1]) - 1;
            commands.add(new DeleteTaskCommand(CommandType.DELETE, index));
            return commands;
        } catch (NoDescriptionException e) {
            commands.add(new UnknownCommand(CommandType.UNKNOWN));
            return commands;
        }
    }

    /**
     * Adds a FindTaskCommand to the list of commands.
     * @param commands the command list to be run
     * @param description the description of the command
     * @return the list of commands
     */
    public static ArrayList<Command> addFindCommand(ArrayList<Command> commands, String description) {
        try {
            String[] keyword = Parser.separateStringByKeyword(description, "find ", true);
            commands.add(new FindTaskCommand(CommandType.FIND, keyword[1].trim()));
            return commands;
        } catch (NoDescriptionException e) {
            commands.add(new UnknownCommand(CommandType.UNKNOWN));
            return commands;
        }
    }
}
