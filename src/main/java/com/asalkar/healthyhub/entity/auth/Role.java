package com.asalkar.healthyhub.entity.auth;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    
    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}