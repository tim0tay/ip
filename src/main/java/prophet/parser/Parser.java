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
     * @param ui the user interface of the chatbot
     * @param str the string representing the input
     * @return CommandType the type of command given in the input
     */
    public static ArrayList<Command> parse(Ui ui, String str)
            throws InvalidTaskNumberException, NoDescriptionException {
        // read first word of string
        // if first word is in commandtype, parse further for args
        String firstWord = str.split(" ", 2)[0].toLowerCase();
        ArrayList<Command> commands = new ArrayList<>();
        switch (firstWord) {
        case "list":
            // list all tasks
            commands.add(new ListCommand(CommandType.LIST));
            return commands;
        case "mark":
            // mark task as done
            try {
                String[] mark = str.split("mark ", 2);
                if (mark.length < 2 || mark[1].isEmpty()) {
                    throw new InvalidTaskNumberException();
                }
                int index = Integer.parseInt(mark[1]) - 1;
                commands.add(new MarkCommand(CommandType.MARK, index));
                return commands;
            } catch (NumberFormatException e) {
                return commands;
            }
        case "unmark":
            // mark task as not done
            try {
                String[] unmark = str.split("unmark ", 2);
                if (unmark.length < 2 || unmark[1].isEmpty()) {
                    throw new InvalidTaskNumberException();
                }
                int index = Integer.parseInt(unmark[1]) - 1;
                commands.add(new MarkNotDoneCommand(CommandType.UNMARK, index));
                return commands;
            } catch (NumberFormatException e) {
                return commands;
            }
        case "todo":
            // add a to do task
            try {
                String[] description = str.split("todo", 2);
                if (description.length < 2 || description[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                commands.add(new AddToDoCommand(CommandType.TODO, description[1].trim()));
                return commands;
            } catch (NoDescriptionException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "t":
            // same as to do, just with a different keyword from reading from save file
            try {
                String[] description = str.split("T \\| ", 2);
                if (description.length < 2 || description[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                String status = description[1].substring(0, 3);
                String[] statusAndDescription = description[1].split("]", 2);
                boolean isDone = status.equals("[X]");
                commands.add(new AddToDoCommand(CommandType.TODO, statusAndDescription[1].trim()));
                if (isDone) {
                    commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                }
                return commands;
            } catch (NoDescriptionException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "deadline":
            // add a deadline task
            try {
                String[] description = str.split("deadline ", 2);
                if (description.length < 2 || description[1].trim().isEmpty()
                        || description[1].trim().startsWith("/by")) {
                    throw new NoDescriptionException();
                }
                String[] remainingParts = description[1].split("/by", 2);
                if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                LocalDate deadline = LocalDate.parse(remainingParts[1].trim());
                commands.add(new AddDeadlineCommand(
                        CommandType.DEADLINE, remainingParts[0].trim(), deadline));
                return commands;
            } catch (NoDescriptionException | DateTimeParseException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "d":
            // same as deadline, just with a different keyword from reading from save file
            try {
                String[] description = str.split("D \\| ", 2);
                if (description.length < 2 || description[1].trim().isEmpty()
                        || description[1].trim().startsWith("by:")) {
                    throw new NoDescriptionException();
                }
                String status = description[1].substring(0, 3);
                String[] statusAndDescription = description[1].split("]", 2);
                boolean isDone = status.equals("[X]");
                String[] remainingParts = statusAndDescription[1].split("by: ", 2);
                if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                LocalDate deadline = LocalDate.parse(
                        remainingParts[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                commands.add(new AddDeadlineCommand(
                        CommandType.DEADLINE, remainingParts[0].trim(), deadline));
                if (isDone) {
                    commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                }
                return commands;
            } catch (NoDescriptionException | DateTimeParseException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "event":
            // add an event task
            try {
                String[] description = str.split("event ", 2);
                if (description.length < 2 || description[1].trim().isEmpty()
                        || description[1].trim().startsWith("/from")) {
                    throw new NoDescriptionException();
                }
                String[] remainingParts = description[1].split("/from ", 2);
                if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                String[] timeline = remainingParts[1].split("/to ", 2);
                if (timeline.length < 2 || timeline[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                LocalDate from = LocalDate.parse(timeline[0].trim());
                LocalDate to = LocalDate.parse(timeline[1].trim());
                commands.add(new AddEventCommand(
                        CommandType.EVENT, remainingParts[0].trim(), from, to));
                return commands;
            } catch (NoDescriptionException | DateTimeParseException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "e":
            // same as event, just with a different keyword from reading from save file
            try {
                String[] description = str.split("E \\| ", 2);
                if (description.length < 2 || description[1].trim().isEmpty()
                        || description[1].trim().startsWith("/from")) {
                    throw new NoDescriptionException();
                }
                String status = description[1].substring(0, 3);
                String[] statusAndDescription = description[1].split("]", 2);
                boolean isDone = status.equals("[X]");
                String[] remainingParts = statusAndDescription[1].split("from: ", 2);
                if (remainingParts.length < 2 || remainingParts[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                String[] timeline = remainingParts[1].trim().split("to: ", 2);
                if (timeline.length < 2 || timeline[1].trim().isEmpty()) {
                    throw new NoDescriptionException();
                }
                LocalDate from = LocalDate.parse(timeline[0].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                LocalDate to = LocalDate.parse(timeline[1].trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                commands.add(new AddEventCommand(
                        CommandType.EVENT, remainingParts[0].trim(), from, to));
                if (isDone) {
                    commands.add(new MarkCommand(CommandType.MARK, Prophet.getStorageSize()));
                }
                return commands;
            } catch (NoDescriptionException | DateTimeParseException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        case "delete":
            // delete a task
            try {
                String[] delete = str.split("delete ", 2);
                if (delete.length < 2 || delete[1].isEmpty()) {
                    throw new InvalidTaskNumberException();
                }
                int index = Integer.parseInt(delete[1]) - 1;
                commands.add(new DeleteTaskCommand(CommandType.DELETE, index));
                return commands;
            } catch (NumberFormatException e) {
                ui.print("The number you keyed in was not an integer! Try again.");
                return commands;
            }
        case "find":
            // find tasks with a keyword
            try {
                String[] keyword = str.split("find ", 2);
                if (keyword.length < 2 || keyword[1].isEmpty()) {
                    throw new NoDescriptionException();
                }
                commands.add(new FindTaskCommand(CommandType.FIND, keyword[1].trim()));
                return commands;
            } catch (NoDescriptionException e) {
                commands.add(new UnknownCommand(CommandType.UNKNOWN));
                return commands;
            }
        default:
            // unknown command
            commands.add(new UnknownCommand(CommandType.UNKNOWN));
            return commands;
        }
    }
}
