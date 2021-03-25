package application.start;

import application.notes.crud.delete.multi.MultiNoteDeletionCommand;
import application.notes.crud.delete.single.SingleNoteDeletionCommand;
import application.notes.crud.edit.single.NoteLineEditorCommand;
import application.notes.crud.read.single.NoteReaderCommand;
import application.notes.dispatch.single.SingleNoteDispatcherCommand;
import application.notes.find.multi.OverviewWordFinderCommand;
import application.notes.find.single.SingleNoteWordFinderCommand;
import application.notes.sort.command.NoteSorterCommand;
import application.notes.spellcheck.multi.OverviewSpellCheckerCommand;
import application.notes.spellcheck.single.SingleNoteSpellcheckerCommand;
import application.notes.ui.frame.DisplayCommand;
import application.notes.wordcount.multi.OverviewCounterCommand;
import application.notes.wordcount.single.SingleNoteCounterCommand;
import application.singleWord.SingleWordSpellCheckerCommand;
import application.notes.crud.declare.single.NoteDeclarationCommand;
import application.start.model.commands.CommandList;
import application.start.model.help.HelpMode;
import application.start.model.specialcommands.helpCommands.ExtendedHelpCommand;
import application.start.model.specialcommands.helpCommands.HelpCommand;
import application.start.model.specialcommands.exitCommand.ProgramExitCommand;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.util.*;
import java.util.stream.Collectors;

import static application.start.NoteAdministryStartMessagePrinter.printErrorMessage;
import static application.start.NoteAdministryStartMessagePrinter.printStartingMessage;
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
        commandList.add(new DisplayCommand("display", "display an overview of all notes in directory" + path_for_notes));
        commandList.add(new NoteLineEditorCommand("edit", "replace a single line of a note"));
        commandList.add(new HelpCommand("help", "display a list of commands"));
        commandList.add(new ExtendedHelpCommand("help+", "display a list of commands with a short description"));
        commandList.add(new NoteReaderCommand("read", "display the content of a note"));
        commandList.add(new SingleNoteWordFinderCommand("search", "get information about the occurrence of a keyword within one specified note"));
        commandList.add(new OverviewWordFinderCommand("search all", "get information about the occurrence of a keyword within all notes"));
        commandList.add(new SingleNoteDispatcherCommand("send", "send a note via email"));
        commandList.add(new SingleWordSpellCheckerCommand("sc sn", "check single word/s for spelling"));
        commandList.add(new SingleNoteSpellcheckerCommand("sc sw", "check the spelling for a specific note"));
        commandList.add(new OverviewSpellCheckerCommand("sc all", "check the spelling for all files in directory" + path_for_notes));
        commandList.add(new NoteSorterCommand("sort", "output sorted overview of files in directory" + path_for_notes));
        commandList.add(new SingleNoteCounterCommand("word count", "count words of all notes in directory" + path_for_notes));
        commandList.add(new OverviewCounterCommand("word count all", "count words of a specific note in directory" + path_for_notes));
        commandList.add(new ProgramExitCommand("exit", "exit program"));
    }

    public static void main(String[] args) {
        programRun = true;
        fillCommands();
        while (programRun) {
            printStartingMessage();
            String commandName = scanner.nextLine();
            makeActiveDecisions(commandName);
            Optional<AbstractCommand> activeCommandOptional = findActiveCommand();
            if (activeCommandOptional.isEmpty()) {
                printErrorMessage(commandName);
                continue;
            }
            AbstractCommand activeCommand = activeCommandOptional.get();
            if (activeCommand.getCommandName().equals("help")) {
                listCommands(HelpMode.BASIC);
                continue;
            }
            if (activeCommand.getCommandName().equals("help+")) {
                listCommands(HelpMode.EXTENDED);
                continue;
            }
            resetCommandActiveFlagFromCommandList();
            activeCommand.execute();
        }
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

    protected static void listCommands(HelpMode helpMode) {
        System.out.println();
        if (helpMode.equals(HelpMode.EXTENDED)) {
            System.out.println(commandList.stream().map(
                    command -> command.getCommandName() + "   |   " +
                            command.getDescription() + "\n").collect(Collectors.joining())
            );
            return;
        }
        System.out.println(commandList.stream().map(
                command -> command.getCommandName() + "    ").collect(Collectors.joining())
        );
        System.out.println();
        resetCommandActiveFlagFromCommandList();
    }
}
