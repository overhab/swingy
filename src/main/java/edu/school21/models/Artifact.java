package edu.school21.models;


import jakarta.persistence.*;

@Entity
@Table(name = "artifact")
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "effect")
    private int effect;

    public Artifact(String name, String type, int effect) {
        this.name = name;
        this.type = type;
        this.effect = effect;
    }

    public Artifact() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getEffect() {
        return effect;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", effect=" + effect +
                '}';
    }
}
