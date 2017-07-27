package com.std.boot.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity(name="app_role")
public class Role {
	
	@Setter
	@Getter
	@Column(name="name",nullable=false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<UserProfile> users;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserProfile> getUsers() {
        return users;
    }

    public void setUsers(Set<UserProfile> users) {
        this.users = users;
    }
}
