package commands;

import tasklist.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends AddTaskWithTimeCommand {

    public DeadlineCommand(String commandBody) {
        super("done", commandBody);
        this.timeArgDelimiter = "/by";
        this.missingThirdArgErrMsg = "missing date/time argument";
    }

    @Override
    public void run(TaskList taskList) {
        try {
            parseCommandBody();
            this.commandOutputMsg = taskList.addTask(new Deadline(secondArg, thirdArg));
        } catch (Exception e) {
            handleException(e);
        }
    }
}
