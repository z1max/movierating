package by.z1max.web.command;

public class UnknownCommand extends Command {
    @Override
    public CommandResponse process() {
        return CommandResponse.forwardUnknown();
    }
}
