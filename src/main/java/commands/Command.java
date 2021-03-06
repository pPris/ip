package commands;

import datetime.KiwiDateTime;
import datetime.ParseKiwiDateTime;
import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import format.Ui;
import tasklist.TaskList;


public abstract class Command {

    private String[] commandList =
            {"bye", "list", "event", "todo", "deadline", "delete", "done"};

    /** For description and toString purposes. Not for switch case or anything that an enum
     * could do better. Should correspond with the command to type in? */
    final String commandName;

    protected final String commandBody;
    protected String commandOutputMsg;
    protected boolean hasRunSuccessfully = false;
    protected boolean hasSentExitDukeSignal = false;

    protected Command(String commandName, String commandBody) {
        this.commandName = commandName;
        this.commandBody = commandBody;
    }

    /**
     * Runs a command and stores the command's output and status in instance variables.
     * @param taskList taskList to call relevant methods on
     */
    public abstract void run(TaskList taskList);

    @Override
    public String toString() {
        return String.format("Command: %s, hasRunSuccessfully: %s, body: %s",
                this.commandName, this.hasRunSuccessfully, this.commandBody);
    }

    public String getCommandOutputMsg() {
        return this.commandOutputMsg;
    }

    /**
     * Returns whether this command has ran successfully
     * @return
     */
    public boolean hasRunSuccessfully() {
        return this.hasRunSuccessfully;
    }

    /**
     * Returns whether any command has sent a signal to exit the app
     * @return
     */
    public boolean hasSentExitDukeSignal() {
        return this.hasSentExitDukeSignal;
    }

    /**
     * Called by commands that can't be used on empty lists, and throws an error
     * after checking if the list is empty.
     * @param isEmpty
     * @throws InvalidArgumentException
     */
    protected void handleInvalidOnEmptyList(boolean isEmpty) throws InvalidArgumentException {
        if (isEmpty) {
            throw new InvalidArgumentException("This command cannot be done on an empty task list.");
        }
    }

    /**
     * Adds a formatted exception message to this command's output message.
     * All exception handlers in command classes should use this abstraction.
     * @param e
     */
    protected void handleException(Exception e) {
        this.commandOutputMsg = Ui.formatException(e.getMessage());
    }

    public void debug() {
        System.out.println(this.commandBody);
    }

    protected KiwiDateTime parseToKiwiDateTime(String s) throws MissingArgumentException {
        ParseKiwiDateTime p = new ParseKiwiDateTime();
        return p.parse(s);
    }

}
