package by.z1max.dto;

import by.z1max.model.Review;

import java.util.Date;
import java.util.List;

public class EagerMovie extends LazyMovie {
    private int budget;
    private String description;
    private List<Review> reviews;

    public EagerMovie(){}

    public EagerMovie(int id, String title, String director, float rating, Date releaseDate, short runtime, String genres, String countries, int budget, String description, List<Review> reviews) {
        super(id, title, director, rating, releaseDate, runtime, genres, countries);
        this.budget = budget;
        this.description = description;
        this.reviews = reviews;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
