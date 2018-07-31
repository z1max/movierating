package by.epam.dto;

import by.epam.model.Role;
import by.epam.model.UserStatus;

import java.util.Set;

public class ActiveUser {
    private Integer id;
    private String username;
    private Set<Role> roles;
    private UserStatus status;

    public ActiveUser(Integer id, String username, Set<Role> roles, UserStatus status) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActiveUser{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
