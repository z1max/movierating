package by.epam.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private LocalDate registered;
    private int points;
    private boolean enabled;
    private Set<Role> roles;

    public User(Integer id, String username, String email, String password, LocalDate registered, int points, boolean enabled) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.points = points;
        this.enabled = enabled;
        this.roles = new HashSet<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public UserStatus getStatus() {
        return UserStatus.getStatus(this.points);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
        return  super.getId().equals(other.getId()) &&
                username.equals(other.username) &&
                email.equals(other.email) &&
                password.equals(other.password) &&
                registered.equals(other.registered) &&
                enabled == other.enabled &&
                points == other.points &&
                roles.equals(other.roles);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int prime = 31;
        hash = prime * hash + (super.getId() == null ? 0 : super.getId());
        hash = prime * hash + (username == null ? 0 : username.hashCode());
        hash = prime * hash + (email == null ? 0 : email.hashCode());
        hash = prime * hash + (password == null ? 0 : password.hashCode());
        hash = prime * hash + (registered == null ? 0 : registered.hashCode());
        hash = prime * hash + points;
        hash = prime * hash + (enabled ? 1 : 0);
        hash = prime * hash + (roles == null ? 0 : roles.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", registered=").append(registered);
        sb.append(", points=").append(points);
        sb.append(", enabled=").append(enabled);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
