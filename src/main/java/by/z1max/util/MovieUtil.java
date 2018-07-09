package by.z1max.util;

import by.z1max.dto.BaseMovie;
import by.z1max.dto.EagerMovie;
import by.z1max.dto.LazyMovie;
import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;
import by.z1max.model.Review;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieUtil {
    public static LazyMovie getFrom(Movie movie, float rating){
        LazyMovie lazyMovie = new LazyMovie();
        getCommonFields(lazyMovie, movie);
        lazyMovie.setRating(rating);
        return lazyMovie;
    }

    public static EagerMovie getFrom(Movie movie, float rating, List<Review> reviews){
        EagerMovie eagerMovie = new EagerMovie();
        getCommonFields(eagerMovie, movie);
        eagerMovie.setRating(rating);
        eagerMovie.setBudget(movie.getBudget());
        eagerMovie.setDescription(movie.getDescription());
        eagerMovie.setReviews(reviews);
        return eagerMovie;
    }

    private static <T extends BaseMovie> void getCommonFields(T instance, Movie movie){
        instance.setId(movie.getId());
        instance.setTitle(movie.getTitle());
        instance.setDirector(movie.getDirector());
        instance.setReleaseDate(asDate(movie.getReleaseDate()));
        instance.setRuntime(movie.getRuntime());
        instance.setGenres(convertGenres(movie.getGenres()));
        instance.setCountries(convertCountries(movie.getCountries()));
    }

    private static Date asDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private static String convertGenres(Set<Genre> genres){
        return genres.stream()
                .map(Genre::toString)
                .collect(Collectors.joining(", "));

    }

    private static String convertCountries(Set<Country> countries){
        return countries.stream()
                .map(Country::getName)
                .collect(Collectors.joining(", "));
    }
}
