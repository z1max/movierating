package by.epam.model;

public class BaseEntity {
    private Integer id;

    public BaseEntity(){}

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return id == null;
    }
}
