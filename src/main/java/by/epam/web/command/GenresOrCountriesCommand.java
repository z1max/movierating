package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.Country;
import by.epam.model.Genre;

import java.util.List;

public class GenresOrCountriesCommand extends Command {
    @Override
    public CommandResponse process() {
        String entity = wrapper.getParameter("entity");
        if ("genres".equals(entity)){
            try {
                List<Genre> genres = appContext.getGenreService().getAll();
                wrapper.setAttribute("entities", genres);
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
            }
            return CommandResponse.newBuilder()
                    .setTarget("genresOrCountries")
                    .build();
        } else if ("countries".equals(entity)){
            try {
                List<Country> countries = appContext.getCountryService().getAll();
                wrapper.setAttribute("entities", countries);
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
            }
            return CommandResponse.newBuilder()
                    .setTarget("genresOrCountries")
                    .build();
        } else {
            wrapper.setAttribute("errorMessageKey", "exception.unknownCommand");
            return CommandResponse.forwardUnknown();
        }
    }
}
