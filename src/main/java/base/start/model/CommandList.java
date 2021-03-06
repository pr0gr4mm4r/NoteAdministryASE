package base.start.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandList extends ArrayList<Command> {

    public void listCommands(HelpMode helpMode) {
        System.out.println();
        if(helpMode.equals(HelpMode.EXTENDED)) {
            System.out.println(this.stream().map(
                    command -> command.getComandName() + "   |   " +
                            command.getDescription() + "\n").collect(Collectors.joining())
            );
            return;
        }
        System.out.println(this.stream().map(
                command -> command.getComandName() + "    ").collect(Collectors.joining())
        );
        System.out.println();
    }
}
