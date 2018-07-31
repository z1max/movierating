package by.epam.web.command;

public class UnknownCommand extends Command {
    @Override
    public CommandResponse process() {
        return CommandResponse.forwardUnknown();
    }
}
