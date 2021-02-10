package commands;

import format.Ui;
import tasklist.TaskList;

public class ByeCommand extends CommandWithNoParameters {

    public ByeCommand(String commandBody) {
        super("bye", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        // make sure that parser always sends trimmed strings?
        assert commandBody.trim().equals(commandBody) : "parser is not sending trimmed strings to commands";

        if (commandBody.isEmpty()) {
            this.hasSentExitDukeSignal = true;
            this.commandOutputMsg = Ui.getExitMessage();
        } else {
            handleTooManyArgs(); // should this be in try-catch block
        }
    }
}
