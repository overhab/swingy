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
    @Column(name = "tier")
    private int tier;

    public Artifact(long id, String name, String type, int effect, int tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.effect = effect;
        this.tier = tier;
    }

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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
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
