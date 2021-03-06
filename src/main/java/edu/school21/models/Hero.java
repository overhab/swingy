package edu.school21.models;

import edu.school21.app.StaticVariables;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Strings;

import java.util.HashMap;

@Entity
@Table(name = "hero")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="hero_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "heroName")
    @Size(min = 3, max = 16, message = "Hero name must be 3 to 16 characters long!")
    protected String name;

    @Column(name = "heroClass")
    @NotNull(message = StaticVariables.HERO_TYPE)
    @Enumerated(value = EnumType.STRING)
    protected HeroClass heroClass;
    @Column(name = "level")
    protected int level;
    @Column(name = "exp")
    protected int experience;
    @Column(name = "attack")
    protected int attack;
    @Column(name = "defence")
    protected int defence;
    @Column(name = "hitpoints")
    protected int hitPoints;
    @Transient
    protected final HashMap<String, Artifact> artifacts = new HashMap<>(3);


    public void setName(String name) {
        this.name = name;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void gainExp(int exp) {
        this.experience += exp;
    }

    public long getId() {
        return id;
    }

    public HashMap<String, Artifact> getArtifacts() {
        return artifacts;
    }

    public int levelUp() {
        setLevel(getLevel() + 1);
        setHitPoints(getHitPoints() + 15);
        setAttack(getAttack() + 3);
        setDefence(getDefence() + 2);
        return getLevel();
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heroClass=" + heroClass +
                ", level=" + level +
                ", experience=" + experience +
                ", attack=" + attack +
                ", defence=" + defence +
                ", hitPoints=" + hitPoints +
                '}';
    }

    public long equipGear(Artifact artifact) {

        if (artifact == null) {
            return 0;
        }

        long ret = 0;

        Artifact tmp = artifacts.get(artifact.getType());

        if (tmp != null) {
            ret = tmp.getId();
            removeGear(tmp.getType());
        }

        artifacts.put(artifact.getType(), artifact);
        if (artifact.getType().equals("Armor")) {
            setDefence(getDefence() + artifact.getEffect());
        } else if (artifact.getType().equals("Helmet")) {
            setHitPoints(getHitPoints() + artifact.getEffect());
        } else if (artifact.getType().equals("Weapon")) {
            setAttack(getAttack() + artifact.getEffect());
        }

        return ret;
    }

    public void removeGear(String gear) {
        Artifact artifact = artifacts.get(gear);
        artifacts.put(gear, null);
        if (gear.equals("Armor")) {
            setDefence(getDefence() - artifact.getEffect());
        } else if (gear.equals("Helmet")) {
            setHitPoints(getHitPoints() - artifact.getEffect());
        } else if (gear.equals("Weapon")) {
            setAttack(getAttack() - artifact.getEffect());
        }
    }

    public String[] toArray() {
        String[] data = new String[6];

        data[0] = getName();
        data[1] = getHeroClass().toString();
        data[2] = String.valueOf(getLevel());
        data[3] = String.valueOf(getAttack());
        data[4] = String.valueOf(getDefence());
        data[5] = String.valueOf(getHitPoints());

        return data;
    }
}
