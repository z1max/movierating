package by.epam.web.command;

public class LogoutCommand extends Command {
    @Override
    public CommandResponse process() {
        return CommandResponse.newBuilder()
                .setTarget("/")
                .setRedirect(true)
                .setInvalidate(true)
                .build();
    }
}
