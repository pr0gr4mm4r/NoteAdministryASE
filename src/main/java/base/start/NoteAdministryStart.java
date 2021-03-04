package base.start;

import base.notes.crud.declare.NoteDeclarator;
import base.notes.crud.delete.NoteDeleter;
import base.notes.crud.delete.SingleNoteDeleter;
import base.notes.crud.read.NoteReader;
import base.notes.crud.edit.NoteLineEditor;
import base.notes.dispatch.custom.SingleNoteDispatcher;
import base.notes.find.OverviewWordFinder;
import base.notes.find.SingleNoteWordFinder;
import base.notes.spellcheck.custom.OverviewSpellChecker;
import base.notes.spellcheck.custom.SingleNoteSpellChecker;
import base.notes.spellcheck.custom.SingleWordSpellchecker;
import base.notes.wordcount.custom.OverviewCounter;
import base.notes.wordcount.custom.SingleNoteCounter;
import base.start.model.Command;
import base.start.model.HelpMode;

import java.util.*;
import java.util.stream.Collectors;

import static base.config.Globals.path;
import static base.config.Globals.scanner;

public class NoteAdministryStart {
    private static List<Command> commandList;
    public static boolean programRun = false;

    private static void listCommands(HelpMode helpMode) {
        System.out.println();
        if(helpMode.equals(HelpMode.EXTENDED)) {
            System.out.println(commandList.stream().map(
                    command -> command.getComandName() + "   |   " +
                            command.getDescription() + "\n").collect(Collectors.joining())
            );
            return;
        }
        System.out.println(commandList.stream().map(
                command -> command.getComandName() + "    ").collect(Collectors.joining())
        );
        System.out.println();
    }

    private static void mapCommandExecution(Command activeCommand) {
        switch (activeCommand.getComandName()) {
            case "declare" -> new NoteDeclarator();
            case "delete" -> new SingleNoteDeleter();
            case "delete all" -> new NoteDeleter();
            case "edit" -> new NoteLineEditor();
            case "help" -> listCommands(HelpMode.BASIC);
            case "help+" -> listCommands(HelpMode.EXTENDED);
            case "read" -> new NoteReader();
            case "search" -> new SingleNoteWordFinder();
            case "search all" -> new OverviewWordFinder();
            case "send" -> new SingleNoteDispatcher();
            case "sc sn" -> new SingleNoteSpellChecker();
            case "sc sw" -> new SingleWordSpellchecker();
            case "sc all" -> new OverviewSpellChecker();
            case "word count" -> new SingleNoteCounter();
            case "word count all" -> new OverviewCounter();
            case "exit" -> programExit();
        }
    }

    private static void fillCommands() {
        commandList = new ArrayList<>();
        commandList.add(new Command("declare", "create a new note with a header containing creating date and time"));
        commandList.add(new Command("delete", "delete a single note in directory" + path));
        commandList.add(new Command("delete all", "delete all notes in directory" + path));
        commandList.add(new Command("edit", "replace a single line of a note"));
        commandList.add(new Command("help", "display a list of commands"));
        commandList.add(new Command("help+", "display a list of commands with a short description"));
        commandList.add(new Command("read", "display the content of a note"));
        commandList.add(new Command("search", "get information about the occurence of a keyword within one specified note"));
        commandList.add(new Command("search all", "get information about the occurence of a keyword within all notes"));
        commandList.add(new Command("send", "send a note via email"));
        commandList.add(new Command("sc sn", "check single word/s for spelling"));
        commandList.add(new Command("sc sw", "check the spelling for a specific note"));
        commandList.add(new Command("sc all", "check the spelling for all files in directory" + path));
        commandList.add(new Command("word count", "count words of all notes in directory"  + path));
        commandList.add(new Command("word count all", "count words of a specific note in directory" + path));
        commandList.add(new Command("exit", "exit program"));
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
            activeDecision(commandName);
            Optional<Command> activeCommand = findActiveCommand();
            if (activeCommand.isEmpty()) {
                printErrorMessage(commandName);
                continue;
            }
            resetCommandList();
            mapCommandExecution(activeCommand.get());
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

    private static void activeDecision(String commandName) {
        commandList.forEach(command -> command.makeActiveDecision(commandName));
    }

    private static Optional<Command> findActiveCommand() {
        return commandList.stream().filter(Command::isActive).findAny();
    }

    private static void resetCommandList() {
        commandList.forEach(command -> command.setActive(false));
    }

    public static boolean programIsRunning() {
        return programRun;
    }

    public static void setProgramRun(boolean programRun) {
        NoteAdministryStart.programRun = programRun;
    }
}
