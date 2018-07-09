package by.z1max.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private LocalDate registered;
    private UserStatus status;
    private boolean enabled;
    private Set<Role> roles;

    public User(Integer id, String username, String email, String password, LocalDate registered, UserStatus status, boolean enabled) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.status = status;
        this.enabled = enabled;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
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

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(super.getId(), user.getId()) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(registered, user.registered) &&
                enabled == user.enabled &&
                status == user.status &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, registered, status, enabled, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", registered=").append(registered);
        sb.append(", status=").append(status);
        sb.append(", enabled=").append(enabled);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
