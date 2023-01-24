package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class CreateDeadlineCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public CreateDeadlineCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = commandMessage.split("/", 2);
            Task task = new Deadline(commandMessageArr[0].substring(9), false,
                    commandMessageArr[1].substring(3));

            taskList.addTask(task);
            storage.storeTask(task);
            ui.replyTaskAdded(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
