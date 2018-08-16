package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.service.CountryService;
import by.epam.service.GenreService;

public class DeleteGenreOrCountryCommand extends Command {
    @Override
    public CommandResponse process() {
        int id = Integer.parseInt(wrapper.getParameter("id"));
        String entity = wrapper.getParameter("entity");
        if ("genres".equals(entity)){
            try {
                GenreService genreService = appContext.getGenreService();
                genreService.delete(id);
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=GenresOrCountries&entity=genres")
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.forwardUnknown();
            }
        } else {
            try {
                CountryService countryService = appContext.getCountryService();
                countryService.delete(id);
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=GenresOrCountries&entity=countries")
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.forwardUnknown();
            }
        }
    }
}
