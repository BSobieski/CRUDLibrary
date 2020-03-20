package pl.bsobieski.crudlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    @Transient
    @NotNull
    private String passwordConfirm;

    @NotNull
    private String roles;

    @JsonIgnore
    @NotNull
    private String permissions;

    public User() {
    }

    public User(@NotNull String username, @NotNull String password, @NotNull String passwordConfirm, @NotNull String roles, @NotNull String permissions) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.permissions = permissions;
    }

    @JsonIgnore
    public List<String> getRoleList(){
        List<String> roleList = Arrays.asList(this.roles.split(","));
        return roleList;
    }

    @JsonIgnore
    public List<String> getPermissionList(){
        List<String> permissionList = Arrays.asList(this.permissions.split(","));
        return permissionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
