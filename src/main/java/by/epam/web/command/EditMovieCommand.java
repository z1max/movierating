package by.epam.web.command;

import by.epam.exception.ServiceException;
import by.epam.model.Country;
import by.epam.model.Genre;
import by.epam.model.Movie;
import by.epam.service.CountryService;
import by.epam.service.GenreService;
import by.epam.service.MovieService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditMovieCommand extends Command {
    @Override
    public CommandResponse process() {
        MovieService movieService = appContext.getMovieService();
        GenreService genreService = appContext.getGenreService();
        CountryService countryService = appContext.getCountryService();
        if ("GET".equals(method)) {
            try {
                List<Genre> genres = genreService.getAll();
                List<Country> countries = countryService.getAll();

                wrapper.setAttribute("genres", genres);
                wrapper.setAttribute("countries", countries);

                if (wrapper.getParameter("movieId") != null) {
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
            int budget = Integer.parseInt(wrapper.getParameter("budget"));
            String description = wrapper.getParameter("description");
            short runtime = Short.parseShort(wrapper.getParameter("runtime"));

            try {
                String[] genresParam = wrapper.getParameterValues("genres");
                Set<Genre> genres = new HashSet<>();
                for (String genre : genresParam) {
                    Genre current = genreService.getByName(genre);
                    genres.add(current);
                }

                String[] countriesParam = wrapper.getParameterValues("countries");
                Set<Country> countries = new HashSet<>();
                for (String country : countriesParam) {
                    Country current = countryService.getByName(country);
                    countries.add(current);
                }

                Movie movie = new Movie(movieId, title, director, releaseDate, budget, description, runtime);
                movie.setGenres(genres);
                movie.setCountries(countries);

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
