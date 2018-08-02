package by.epam.model;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review other = (Review) o;
        return super.getId().equals(other.getId()) &&
                userId == other.userId &&
                movieId == other.movieId &&
                username.equals(other.username) &&
                comment.equals(other.comment) &&
                date.equals(other.date);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int prime = 31;
        hash = prime * hash + (super.getId() == null ? 0 : super.getId().hashCode());
        hash = prime * hash + userId;
        hash = prime * hash + ( username == null ? 0 : username.hashCode());
        hash = prime * hash + movieId;
        hash = prime * hash + (comment == null ? 0 : comment.hashCode());
        hash = prime * hash + (date == null ? 0 : date.hashCode());
        return hash;
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
