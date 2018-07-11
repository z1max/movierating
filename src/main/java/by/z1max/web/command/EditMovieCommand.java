package by.z1max.web.command;

import by.z1max.exception.ServiceException;
import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;
import by.z1max.util.Context;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EditMovieCommand extends Command {
    @Override
    public void process(Context context) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")){
            request.setAttribute("genres", Genre.values());
            request.setAttribute("countries", Country.values());
            if (request.getParameter("movieId") != null){
                try {
                    request.setAttribute("movie", context.getMovieService().get(Integer.parseInt(request.getParameter("movieId"))));
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            forward("editMovie");
        }
        if (method.equals("POST")){
            String movieIdParam = request.getParameter("movieId");
            Integer movieId = movieIdParam.equals("") ? null : Integer.valueOf(movieIdParam);
            String title = request.getParameter("title");
            String director = request.getParameter("director");
            LocalDate releaseDate = LocalDate.parse(request.getParameter("releaseDate"));
            int budget = Integer.valueOf(request.getParameter("budget"));
            String description = request.getParameter("description");
            short runtime = Short.valueOf(request.getParameter("runtime"));

            String[] genresParam = request.getParameterValues("genres");
            Set<Genre> genres = new HashSet<>();
            for (String genre : genresParam){
                genres.add(Genre.valueOf(genre));
            }

            String[] countriesParam = request.getParameterValues("countries");
            Set<Country> countries = new HashSet<>();
            for (String country : countriesParam){
                countries.add(Country.valueOf(country));
            }

            Movie movie = new Movie(movieId, title, director, releaseDate, budget, description, runtime);
            movie.setGenres(genres);
            movie.setCountries(countries);

            try {
                int id = context.getMovieService().save(movie).getId();
                response.sendRedirect("front?command=Details&id=" + id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        }
    }
}
