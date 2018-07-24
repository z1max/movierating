package by.z1max.web.command;

public class LogoutCommand extends Command {
    @Override
    public CommandResponse process() {
        return CommandResponse.newBuilder()
                .setTarget("index")
                .setInvalidate(true)
                .build();
    }
}
