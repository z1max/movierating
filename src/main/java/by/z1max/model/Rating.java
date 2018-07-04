package by.z1max.model;

public class Rating {
    private int userId;
    private int movieId;
    private byte rating;

    public Rating(int userId, int movieId, byte rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("userId=").append(userId);
        sb.append(", movieId=").append(movieId);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
