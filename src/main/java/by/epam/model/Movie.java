package by.epam.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Movie extends BaseEntity {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private int budget;
    private String description;
    private short runtime;
    private Set<Genre> genres;
    private Set<Country> countries;
    private List<Review> reviews;

    public Movie(){}

    public Movie(Integer id, String title, String director, LocalDate releaseDate, int budget, String description, short runtime) {
        super(id);
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.description = description;
        this.runtime = runtime;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getRuntime() {
        return runtime;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie other = (Movie) o;
        return budget == other.budget &&
                runtime == other.runtime &&
                title.equals(other.title) &&
                director.equals(other.director) &&
                releaseDate.equals(other.releaseDate) &&
                description.equals(other.description) &&
                genres.equals(other.genres) &&
                countries.equals(other.countries) &&
                reviews.equals(other.reviews);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int prime = 31;
        hash = prime * hash + (super.getId() == null ? 0 : super.getId().hashCode());
        hash = prime * hash + (title == null ? 0 : title.hashCode());
        hash = prime * hash + (director == null ? 0 : director.hashCode());
        hash = prime * hash + (releaseDate == null ? 0 : releaseDate.hashCode());
        hash = prime * hash + budget;
        hash = prime * hash + (description == null ? 0 : description.hashCode());
        hash = prime * hash + (int) runtime;
        hash = prime * hash + (genres == null ? 0 : genres.hashCode());
        hash = prime * hash + (countries == null ? 0 : countries.hashCode());
        hash = prime * hash + (reviews == null ? 0 : reviews.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", budget=").append(budget);
        sb.append(", description='").append(description).append('\'');
        sb.append(", runtime=").append(runtime);
        sb.append(", genres=").append(genres);
        sb.append(", countries=").append(countries);
        sb.append(", reviews=").append(reviews);
        sb.append('}');
        return sb.toString();
    }
}
