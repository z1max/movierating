package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.Country;
import by.epam.model.Genre;
import by.epam.model.Movie;
import by.epam.service.MovieService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EditMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        MovieService movieService = appContext.getMovieService();
        if ("GET".equals(method)){
            try {
                wrapper.setAttribute("genres", appContext.getGenreService().getAll());
                wrapper.setAttribute("countries", appContext.getCountryService().getAll());
                if (wrapper.getParameter("movieId") != null){
                    int movieId = Integer.parseInt(wrapper.getParameter("movieId"));
                    Movie movie = movieService.get(movieId);
                    wrapper.setAttribute("movie", movie);
                }
            } catch (ServiceException e) {
                wrapper.setAttribute("errorMessageKey", e.getMessage());
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
                try {
                    Genre current = appContext.getGenreService().getByName(genre);
                    genres.add(current);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }

            String[] countriesParam = wrapper.getParameterValues("countries");
            Set<Country> countries = new HashSet<>();
            for (String country : countriesParam){
                try {
                    Country current = appContext.getCountryService().getByName(country);
                    countries.add(current);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }

            Movie movie = new Movie(movieId, title, director, releaseDate, budget, description, runtime);
            movie.setGenres(genres);
            movie.setCountries(countries);

            try {
                int id = movieService.save(movie).getId();
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
