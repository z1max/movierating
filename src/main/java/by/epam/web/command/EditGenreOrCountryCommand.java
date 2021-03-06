package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.Country;
import by.epam.model.Genre;
import by.epam.service.CountryService;
import by.epam.service.GenreService;

public class EditGenreOrCountryCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.parseInt(wrapper.getParameter("id"));
        String name = wrapper.getParameter("name");
        String entity = wrapper.getParameter("entity");
        if ("genres".equals(entity)){
            try {
                GenreService genreService = appContext.getGenreService();
                genreService.save(new Genre(id, name));
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=GenresOrCountries&entity=genres")
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.forwardError();
            }
        } else {
            try {
                CountryService countryService = appContext.getCountryService();
                countryService.save(new Country(id, name));
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=GenresOrCountries&entity=countries")
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.forwardError();
            }
        }
    }
}
