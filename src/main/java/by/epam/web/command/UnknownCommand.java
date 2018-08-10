package by.epam.web.command;

public class UnknownCommand extends Command {
    @Override
    public CommandResponse process() {

        wrapper.setAttribute("errorMessageKey", "exception.unknownCommand");
        return CommandResponse.forwardUnknown();
    }
}
