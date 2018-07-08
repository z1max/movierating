package by.z1max.web.command;

import by.z1max.util.db.DataSource;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends Command {
    @Override
    public void process(DataSource dataSource) throws ServletException, IOException {
        forward("index");
    }
}
