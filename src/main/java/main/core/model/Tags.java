package main.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    private String name;

    @Transient
    private double weight;

    @OneToMany(mappedBy = "tag")
    @JsonIgnore
    private List<Tag2Post> tag2Posts;

    public List<Tag2Post> getTag2Posts() {
        return tag2Posts;
    }

    public void setTag2Posts(List<Tag2Post> tag2Posts) {
        this.tag2Posts = tag2Posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
