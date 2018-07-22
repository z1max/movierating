package by.z1max.web.command;

import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        forward("unknown");
    }
}
