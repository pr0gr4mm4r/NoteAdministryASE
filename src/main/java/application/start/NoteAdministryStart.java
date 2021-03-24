package application.start;

import application.notes.crud.declare.caller.NoteDeclaratorCaller;
import application.notes.crud.delete.caller.NoteDeleterCaller;
import application.notes.crud.delete.caller.SingleNoteDeleterCaller;
import application.notes.crud.edit.caller.NoteLineEditorCaller;
import application.notes.crud.read.caller.NoteReaderCaller;
import application.notes.dispatch.caller.SingleNoteDispatcherCaller;
import application.notes.find.caller.SingleNoteWordFinderCaller;
import application.notes.find.caller.OverviewWordFinderCaller;
import application.notes.sort.caller.NoteSorterCaller;
import application.notes.spellcheck.caller.OverviewSpellCheckerCaller;
import application.notes.spellcheck.caller.SingleNoteSpellCheckerCaller;
import application.notes.wordcount.caller.OverviewCounterCaller;
import application.notes.wordcount.caller.SingleNoteCounterCaller;
import application.singleWord.SingleWordSpellCheckerCaller;
import application.notes.wordcount.multi.OverviewCounter;
import application.notes.wordcount.single.SingleNoteCounter;
import application.start.model.Command;
import application.start.model.CommandList;
import application.start.model.HelpMode;
import application.notes.ui.frame.DisplayNotes;

import java.util.*;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteAdministryStart {
    private static CommandList commandList;
    public static boolean programRun = false;

    private static void mapCommandExecution(Command activeCommand) {
        switch (activeCommand.getComandName()) {
            case "declare" -> new NoteDeclaratorCaller();
            case "delete" -> new SingleNoteDeleterCaller();
            case "delete all" -> new NoteDeleterCaller();
            case "display" -> new DisplayNotes();
            case "edit" -> new NoteLineEditorCaller();
            case "help" -> commandList.listCommands(HelpMode.BASIC);
            case "help+" -> commandList.listCommands(HelpMode.EXTENDED);
            case "read" -> new NoteReaderCaller();
            case "search" -> new SingleNoteWordFinderCaller();
            case "search all" -> new OverviewWordFinderCaller();
            case "send" -> new SingleNoteDispatcherCaller();
            case "sc sn" -> new SingleNoteSpellCheckerCaller();
            case "sc sw" -> new SingleWordSpellCheckerCaller();
            case "sc all" -> new OverviewSpellCheckerCaller();
            case "sort" -> new NoteSorterCaller();
            case "word count" -> new SingleNoteCounterCaller();
            case "word count all" -> new OverviewCounterCaller();
            case "exit" -> programExit();
        }
    }

    private static void fillCommands() {
        commandList = new CommandList();
        commandList.add(new Command("declare", "create a new note with a header containing creating date and time"));
        commandList.add(new Command("delete", "delete a single note in directory" + path_for_notes));
        commandList.add(new Command("delete all", "delete all notes in directory" + path_for_notes));
        commandList.add(new Command("display", "display an overview of all notes in directory" + path_for_notes));
        commandList.add(new Command("edit", "replace a single line of a note"));
        commandList.add(new Command("help", "display a list of commands"));
        commandList.add(new Command("help+", "display a list of commands with a short description"));
        commandList.add(new Command("read", "display the content of a note"));
        commandList.add(new Command("search", "get information about the occurence of a keyword within one specified note"));
        commandList.add(new Command("search all", "get information about the occurence of a keyword within all notes"));
        commandList.add(new Command("send", "send a note via email"));
        commandList.add(new Command("sc sn", "check single word/s for spelling"));
        commandList.add(new Command("sc sw", "check the spelling for a specific note"));
        commandList.add(new Command("sc all", "check the spelling for all files in directory" + path_for_notes));
        commandList.add(new Command("sort", "output sorted overview of files in directory" + path_for_notes));
        commandList.add(new Command("word count", "count words of all notes in directory" + path_for_notes));
        commandList.add(new Command("word count all", "count words of a specific note in directory" + path_for_notes));
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
            makeActiveDecisions(commandName);
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

    private static void makeActiveDecisions(String commandName) {
        commandList.forEach(command -> command.makeActiveDecision(commandName));
    }

    private static Optional<Command> findActiveCommand() {
        return commandList.stream().filter(Command::isActive).findAny();
    }

    private static void resetCommandList() {
        commandList.forEach(command -> command.setActive(false));
    }
}
