package application.start.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandList extends ArrayList<AbstractCommand> {

    public void listCommands(HelpMode helpMode) {
        System.out.println();
        if(helpMode.equals(HelpMode.EXTENDED)) {
            System.out.println(this.stream().map(
                    command -> command.getCommandName() + "   |   " +
                            command.getDescription() + "\n").collect(Collectors.joining())
            );
            return;
        }
        System.out.println(this.stream().map(
                command -> command.getCommandName() + "    ").collect(Collectors.joining())
        );
        System.out.println();
    }

    public void resetCommands(){
       this.forEach(command -> command.makeActiveDecision("e"));
    }
}
