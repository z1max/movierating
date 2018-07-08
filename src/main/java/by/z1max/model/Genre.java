package by.z1max.model;

public enum Genre {
    ACTION,
    ADVENTURE,
    SCI_FI,
    COMEDY,
    DRAMA,
    ROMANCE,
    CRIME,
    FANTASY,
    THRILLER,
    WAR,
    HORROR,
    MYSTERY,
    ANIMATION,
    HISTORY,
    BIOGRAPHY,
    SPORT,
    WESTERN;

    @Override
    public String toString(){
        String upper = super.toString();
        return upper.charAt(0) + upper.substring(1).toLowerCase().replaceAll("_","-");
    }
}
