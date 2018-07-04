package by.z1max.dto;

import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;

import java.time.LocalDate;
import java.util.Set;

public class LazyMovie {
    private int id;
    private String title;
    private String director;
    private float rating;
    private LocalDate releaseDate;
    private short runtime;
    private Set<Genre> genres;
    private Set<Country> countries;

    public LazyMovie(int id, String title, String director, float rating, LocalDate releaseDate, short runtime, Set<Genre> genres, Set<Country> countries) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
        this.countries = countries;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public short getRuntime() {
        return runtime;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Country> getCountries() {
        return countries;
    }
}
