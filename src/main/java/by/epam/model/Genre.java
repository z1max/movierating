package by.epam.model;

public class Genre extends BaseEntity {
    private String name;

    public Genre(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Genre other = (Genre) o;
        return super.getId().equals(other.getId()) && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int prime = 31;
        hash = prime * hash + (super.getId() == null ? 0 : super.getId().hashCode());
        hash = prime * hash + (name == null ? 0 : name.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
