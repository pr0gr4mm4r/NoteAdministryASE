package application.start;

import application.notes.crud.delete.multi.MultiNoteDeletionCommand;
import application.notes.crud.delete.single.SingleNoteDeletionCommand;
import application.notes.crud.edit.single.SingleNoteLineEditorCommand;
import application.notes.crud.read.single.NoteReaderCommand;
import application.notes.dispatch.single.SingleNoteDispatcherCommand;
import application.notes.find.multi.OverviewWordFinderCommand;
import application.notes.find.single.SingleNoteWordFinderCommand;
import application.notes.spellcheck.multi.OverviewSpellCheckerCommand;
import application.notes.spellcheck.single.SingleNoteSpellcheckerCommand;
import application.notes.ui.frame.DisplayCommand;
import application.notes.wordcount.multi.OverviewCounterCommand;
import application.notes.wordcount.single.SingleNoteCounterCommand;
import application.singleWord.SingleWordSpellCheckerCommand;
import application.start.model.*;
import application.notes.crud.declare.single.NoteDeclarationCommand;
import application.start.model.specialCommands.helpCommands.ExtendedHelpCommand;
import application.start.model.specialCommands.helpCommands.HelpCommand;
import application.start.model.specialCommands.exitCommand.ProgramExitCommand;
import application.start.model.specialCommands.abstractCommand.AbstractCommand;

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
        commandList.add(new HelpCommand("display", "display an overview of all notes in directory" + path_for_notes));
        commandList.add(new SingleNoteLineEditorCommand("edit", "replace a single line of a note"));
        commandList.add(new DisplayCommand("help", "display a list of commands"));
        commandList.add(new ExtendedHelpCommand("help+", "display a list of commands with a short description"));
        commandList.add(new NoteReaderCommand("read", "display the content of a note"));
        commandList.add(new SingleNoteWordFinderCommand("search", "get information about the occurrence of a keyword within one specified note"));
        commandList.add(new OverviewWordFinderCommand("search all", "get information about the occurrence of a keyword within all notes"));
        commandList.add(new SingleNoteDispatcherCommand("send", "send a note via email"));
        commandList.add(new SingleWordSpellCheckerCommand("sc sn", "check single word/s for spelling"));
        commandList.add(new SingleNoteSpellcheckerCommand("sc sw", "check the spelling for a specific note"));
        commandList.add(new OverviewSpellCheckerCommand("sc all", "check the spelling for all files in directory" + path_for_notes));
      //  commandList.add(new Command("sort", "output sorted overview of files in directory" + path_for_notes));
        commandList.add(new OverviewCounterCommand("word count", "count words of all notes in directory" + path_for_notes));
        commandList.add(new SingleNoteCounterCommand("word count all", "count words of a specific note in directory" + path_for_notes));
        commandList.add(new ProgramExitCommand("exit", "exit program"));
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
