package com.angulartry.lexter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
// @Table(name = "users")
public class User {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    // @JsonIgnore
    // private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public User() {
        this.name = "xd";
        this.email = "xd";
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }

    // @JsonAnyGetter
    // public Map<String, Object> getAdditionalProperties() {
    //     return this.additionalProperties;
    // }

    // @JsonAnySetter
    // public void setAdditionalProperty(String name, Object value) {
    //     this.additionalProperties.put(name, value);
    // }
}