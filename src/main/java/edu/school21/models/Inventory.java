package edu.school21.models;


import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hero_id")
    long heroId;

    @Column(name = "item_id")
    long itemId;

    public Inventory() {
    }

    public Inventory(long heroId, long itemId) {
        this.heroId = heroId;
        this.itemId = itemId;
    }

    public Inventory(long id, long heroId, long itemId) {
        this.id = id;
        this.heroId = heroId;
        this.itemId = itemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHeroId() {
        return heroId;
    }

    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
