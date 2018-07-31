package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.Country;
import by.epam.model.Genre;
import by.epam.model.Movie;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EditMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        if ("GET".equals(method)){
            wrapper.setAttribute("genres", Genre.values());
            wrapper.setAttribute("countries", Country.values());
            if (wrapper.getParameter("movieId") != null){
                try {
                    wrapper.setAttribute("movie", appContext.getMovieService().get(Integer.parseInt(wrapper.getParameter("movieId"))));
                } catch (ServiceException e) {
                    wrapper.setAttribute("errorMessageKey", e.getMessage());
                }
            }
            return CommandResponse.newBuilder()
                    .setTarget("editMovie")
                    .build();
        } else {
            String movieIdParam = wrapper.getParameter("movieId");
            Integer movieId = movieIdParam.equals("") ? null : Integer.valueOf(movieIdParam);
            String title = wrapper.getParameter("title");
            String director = wrapper.getParameter("director");
            LocalDate releaseDate = LocalDate.parse(wrapper.getParameter("releaseDate"));
            int budget = Integer.valueOf(wrapper.getParameter("budget"));
            String description = wrapper.getParameter("description");
            short runtime = Short.valueOf(wrapper.getParameter("runtime"));

            String[] genresParam = wrapper.getParameterValues("genres");
            Set<Genre> genres = new HashSet<>();
            for (String genre : genresParam){
                genres.add(Genre.valueOf(genre));
            }

            String[] countriesParam = wrapper.getParameterValues("countries");
            Set<Country> countries = new HashSet<>();
            for (String country : countriesParam){
                countries.add(Country.valueOf(country));
            }

            Movie movie = new Movie(movieId, title, director, releaseDate, budget, description, runtime);
            movie.setGenres(genres);
            movie.setCountries(countries);

            try {
                int id = appContext.getMovieService().save(movie).getId();
                return CommandResponse.newBuilder()
                        .setTarget("/front?command=Details&id=" + id)
                        .setRedirect(true)
                        .build();
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
                return CommandResponse.newBuilder()
                        .setTarget("editMovie")
                        .build();
            }
        }
    }
}
