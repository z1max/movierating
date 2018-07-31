package by.epam.dto;

import java.time.LocalDate;

public class LazyMovie {
    private int id;
    private String title;
    private String director;
    private float rating;
    private LocalDate releaseDate;
    private short runtime;
    private String genres;
    private String countries;

    public LazyMovie(){}

    public LazyMovie(int id, String title, String director, float rating, LocalDate releaseDate, short runtime, String genres, String countries) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public short getRuntime() {
        return runtime;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }
}
