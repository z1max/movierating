package by.z1max.util.db;

public enum DBParameter {
    DRIVER("db.driver"),
    URL("db.url"),
    USER("db.user"),
    PASSWORD("db.password"),
    POOLSIZE("db.poolsize");

    private String parameter;

    DBParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
