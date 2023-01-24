package duke.command;

import duke.Ui;

public class EndChatCommand extends Command{
    private Ui ui;

    public EndChatCommand(String commandMessage, Ui ui) {
        super(commandMessage);
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        ui.endChat();
        return true;
    }
}
