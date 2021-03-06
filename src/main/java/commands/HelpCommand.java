package commands;

import format.Ui;
import tasklist.TaskList;

public class HelpCommand extends CommandWithNoParameters {
    private final String helpMessage =
            Ui.formatMultiLineMessages("Here are all the inputs you can enter: " , // there's a bug in formatting this
                    "replace words in curly braces as appropriate" ,
                    "list" ,
                    "todo {description}" ,
                    "event {description} /at {DD-MM hh:mm}" ,
                    "deadline {description} /by {DD-MM hh:mm}" ,
                    "done {task number}" ,
                    "delete {task number}" ,
                    "find {keyword}",
                    "End of command list.");

    public HelpCommand(String commandBody) {
        super("help", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        this.commandOutputMsg = helpMessage;
        this.hasRunSuccessfully = true;
    }
}
