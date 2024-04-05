package spring.java_lab8.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    private Long id;

    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){
    }

    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
