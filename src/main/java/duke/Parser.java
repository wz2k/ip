package duke;

import duke.command.Command;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EndChatCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.task.TaskList;

public class Parser {
    public static Command parseCommand(String input, Ui ui, TaskList taskList, Storage storage)
            throws DukeException {
        String type = input.split(" ", 2)[0];

        switch(type) {
        case "todo":
            return new CreateTodoCommand(input, ui, taskList, storage);
        case "deadline":
            return new CreateDeadlineCommand(input, ui, taskList, storage);
        case "event":
            return new CreateEventCommand(input, ui, taskList, storage);
        case "mark":
            return new MarkTaskCommand(input, ui, taskList, storage);
        case "unmark":
            return new UnmarkTaskCommand(input, ui, taskList, storage);
        case "delete":
            return new DeleteTaskCommand(input, ui, taskList, storage);
        case "list":
            return new ListTasksCommand(input, ui, taskList);
        case "bye":
            return new EndChatCommand(input, ui);
        default:
            throw new DukeException("Input is not recognized.");
        }
    }
}
