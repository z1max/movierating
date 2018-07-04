package by.z1max.dto;

import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Review;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class EagerMovie {
    private int id;
    private String title;
    private String director;
    private float rating;
    private LocalDate releaseDate;
    private int budget;
    private String description;
    private short runtime;
    private Set<Genre> genres;
    private Set<Country> countries;
    private List<Review> reviews;

    public EagerMovie(int id, String title, String director, float rating, LocalDate releaseDate, int budget, String description, short runtime, Set<Genre> genres, Set<Country> countries, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.description = description;
        this.runtime = runtime;
        this.genres = genres;
        this.countries = countries;
        this.reviews = reviews;
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

    public float getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public String getDescription() {
        return description;
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

    public List<Review> getReviews() {
        return reviews;
    }
}
