package by.epam.model;

public enum UserStatus {
    NEW(0),
    BRONZE(10),
    SILVER(30),
    GOLD(60),
    PLATINUM(100);

    private int points;

    UserStatus(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public static UserStatus getStatus(int points) {
        if (points < BRONZE.getPoints()){
            return NEW;
        }
        if (points < SILVER.getPoints()){
            return BRONZE;
        }
        if (points < GOLD.getPoints()){
            return SILVER;
        }
        if (points < PLATINUM.getPoints()){
            return GOLD;
        } else {
            return PLATINUM;
        }
    }
}
