package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import tasklist.TaskList;

public class DoneCommand extends CommandWithParameters {
    public DoneCommand(String commandBody) {
        super("done", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {

            handleNoArgs();

            handleInvalidOnEmptyList(taskList.isEmpty());

            int userInputIdx = Integer.parseInt(this.commandBody);

            checkInvalidListIdx(taskList.size(), userInputIdx);

            this.commandOutputMsg = taskList.markDone(userInputIdx);

        } catch (NumberFormatException e) {
            handleException(new InvalidArgumentException("Please enter a valid task number to mark done."));
        } catch (InvalidArgumentException | MissingArgumentException e) {
            handleException(e);
        }

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
