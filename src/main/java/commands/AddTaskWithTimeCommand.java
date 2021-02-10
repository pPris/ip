package commands;

import exceptions.MissingArgumentException;
import tasklist.TaskList;

// can be generalised to two arg command
// need to make it clear whether you're counting "todo" as an argument
public abstract class AddTaskWithTimeCommand extends CommandWithParameters {

    protected String timeArgDelimiter = "";
    protected String secondArg;
    protected String thirdArg;
    protected String missingThirdArgErrMsg = "Missing third argument";
    protected String missingDelimiterErrMsg =
            "Missing delimiter / time argument. Please use this command this way: " + this.methodUsage;
    
    protected AddTaskWithTimeCommand(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    private void handleMissingDelimiter() throws MissingArgumentException {
        if (!this.commandBody.contains(this.timeArgDelimiter)) {
            throw new MissingArgumentException(missingDelimiterErrMsg);
        }
    }

    private void splitArgs() {
        int thirdArgIdx = this.commandBody.indexOf(this.timeArgDelimiter);
        this.secondArg = this.commandBody.substring(0, thirdArgIdx).trim();
        this.thirdArg = this.commandBody.substring(thirdArgIdx + this.timeArgDelimiter.length()).trim();
    }

    private void handleEmptyArgs() throws MissingArgumentException {
        if (this.secondArg.isEmpty()) {
            throw new MissingArgumentException("Missing description");
        }

        if (this.thirdArg.isEmpty()) {
            throw new MissingArgumentException(missingThirdArgErrMsg);
        }
    }

    protected void parseCommandBody() throws MissingArgumentException {
        handleMissingDelimiter();

        splitArgs();

        handleEmptyArgs();

        this.hasRunSuccessfully = true;
    }
}
