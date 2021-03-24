package application.start;

import application.notes.crud.delete.multi.MultiNoteDeletionCommand;
import application.notes.crud.delete.single.SingleNoteDeletionCommand;
import application.start.model.AbstractCommand;
import application.notes.crud.declare.single.NoteDeclarationCommand;
import application.start.model.CommandList;

import java.util.*;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteAdministryStart {
    private static CommandList commandList;
    public static boolean programRun = false;

    private static void fillCommands() {
        commandList = new CommandList();
        commandList.add(new NoteDeclarationCommand("declare", "create a new note with a header containing creating date and time"));
        commandList.add(new SingleNoteDeletionCommand("delete", "delete a single note in directory" + path_for_notes));
        commandList.add(new MultiNoteDeletionCommand("delete all", "delete all notes in directory" + path_for_notes));
        commandList.add(new Command("display", "display an overview of all notes in directory" + path_for_notes));
        commandList.add(new Command("edit", "replace a single line of a note"));
        commandList.add(new Command("help", "display a list of commands"));
        commandList.add(new Command("help+", "display a list of commands with a short description"));
        commandList.add(new Command("read", "display the content of a note"));
        commandList.add(new Command("search", "get information about the occurrence of a keyword within one specified note"));
        commandList.add(new Command("search all", "get information about the occurrence of a keyword within all notes"));
        commandList.add(new Command("send", "send a note via email"));
        commandList.add(new Command("sc sn", "check single word/s for spelling"));
        commandList.add(new Command("sc sw", "check the spelling for a specific note"));
        commandList.add(new Command("sc all", "check the spelling for all files in directory" + path_for_notes));
        commandList.add(new Command("sort", "output sorted overview of files in directory" + path_for_notes));
        commandList.add(new Command("word count", "count words of all notes in directory" + path_for_notes));
        commandList.add(new Command("word count all", "count words of a specific note in directory" + path_for_notes));
        commandList.add(new Command("exit", "exit program"));*/
    }

    private static void programExit() {
        scanner.close();
        System.out.println("Exited program");
        programRun = false;
    }

    public static void main(String[] args) {
        programRun = true;
        fillCommands();
        while (programRun) {
            printStartingMessage();
            String commandName = scanner.nextLine();
            makeActiveDecisions(commandName);
            Optional<AbstractCommand> activeCommand = findActiveCommand();
            if (activeCommand.isEmpty()) {
                printErrorMessage(commandName);
                continue;
            }
            resetCommandActiveFlagFromCommandList();
            activeCommand.get().execute();
        }
    }

    private static void printStartingMessage() {
        System.out.println("Enter a command of your choice" +
                " (command 'help' for help)");
    }

    private static void printErrorMessage(String commandName) {
        System.out.println();
        System.out.println("error -> command '" + commandName + "' does not exist!");
        System.out.println();
    }

    private static void makeActiveDecisions(String commandName) {
        commandList.forEach(command -> command.makeActiveDecision(commandName));
    }

    private static Optional<AbstractCommand> findActiveCommand() {
        return commandList.stream().filter(AbstractCommand::isActive).findAny();
    }

    private static void resetCommandActiveFlagFromCommandList() {
        commandList.forEach(command -> command.setActive(false));
    }
}
