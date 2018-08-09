package by.epam.model;

public class Country extends BaseEntity {
    private String name;

    public Country(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country() {
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
        Country other = (Country) o;
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
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
