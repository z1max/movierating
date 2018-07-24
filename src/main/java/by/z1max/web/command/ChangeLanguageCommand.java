package by.z1max.web.command;

public class ChangeLanguageCommand extends Command {
    @Override
    public CommandResponse process() {
        String language = wrapper.getParameter("language");
        wrapper.setSessionAttribute("language", language);
        return CommandResponse.forwardIndex();
    }
}
