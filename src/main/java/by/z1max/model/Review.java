package by.z1max.model;

import java.time.LocalDate;

public class Review extends BaseEntity {
    private int userId;
    private String username;
    private int movieId;
    private String comment;
    private LocalDate date;

    public Review(Integer id, int userId, String username, int movieId, String comment, LocalDate date) {
        super(id);
        this.userId = userId;
        this.movieId = movieId;
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    public Review(int userId, int movieId, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
