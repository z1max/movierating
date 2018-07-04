package by.z1max.util;

import by.z1max.dto.EagerMovie;
import by.z1max.dto.LazyMovie;
import by.z1max.model.Movie;
import by.z1max.model.Review;

import java.util.List;

public class MovieUtil {
    public static LazyMovie getFrom(Movie movie, float rating){
        return new LazyMovie(movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                rating,
                movie.getReleaseDate(),
                movie.getRuntime(),
                movie.getGenres(),
                movie.getCountries());
    }

    public static EagerMovie getFrom(Movie movie, float rating, List<Review> reviews){
        return new EagerMovie(movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                rating,
                movie.getReleaseDate(),
                movie.getBudget(),
                movie.getDescription(),
                movie.getRuntime(),
                movie.getGenres(),
                movie.getCountries(),
                reviews);
    }
}
