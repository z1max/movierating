package by.epam.model;

public enum Country {
    US("USA"),
    DE("Germany"),
    AU("Australia"),
    BR("Brazil"),
    FR("France"),
    UK("United Kingdom"),
    AE("United Arab Emirates"),
    BE("Belgium"),
    CN("China"),
    NZ("New Zealand"),
    IT("Italy"),
    HK("Hong Kong");

    private String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
