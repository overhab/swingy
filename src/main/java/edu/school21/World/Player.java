package edu.school21.World;

import edu.school21.app.StaticVariables;
import edu.school21.models.Artifact;
import edu.school21.models.Hero;
import edu.school21.models.Inventory;
import edu.school21.services.ArtifactService;
import edu.school21.services.InventoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Player {
    private final Hero hero;
    private final Random random = new Random();
    private final InventoryService inventoryService;
    private final ArtifactService artifactService;
    private int levelUp;
    private boolean nextLevel;
    private int expGained;

    public Player(Hero hero, ArtifactService artifactService) {
        this.hero = hero;
        levelUp = (int) ((hero.getLevel() * 1000) + (Math.pow(hero.getLevel() - 1, 2.0) * 450));
        nextLevel = false;
        inventoryService = new InventoryService();
        this.artifactService = artifactService;
    }

    public int getExpGained() {
        return expGained;
    }

    private void gainExp(int exp) {
        System.out.println("You gained " + exp + " experience");
        expGained = exp;
        hero.gainExp(exp);
        if (hero.getExperience() >= levelUp) {
            nextLevel = true;
            hero.levelUp();
            levelUp = (int) ((hero.getLevel() * 1000) + (Math.pow(hero.getLevel() - 1, 2.0) * 450));
//            hero.setExperience(0); // maybe
        }
    }

    public Hero getHero() {
        return hero;
    }

    public int getHitPoints() {
        return hero.getHitPoints();
    }

    public int getLevelUp() {
        return levelUp;
    }

    public String getHeroName() {
        return hero.getName();
    }

    public int getHeroLevel() {
        return hero.getLevel();
    }

    public int getHeroExp() {
        return hero.getExperience();
    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getAttack() {
        return hero.getAttack();
    }

    public int getDefence() {
        return hero.getDefence();
    }

    public int encounter(int type) {
        int ret = 1;

        if (type == 0) {
            return ret;
        }

        int eAttack = hero.getLevel() * Math.max(random.nextInt(5 * type), 2 * type);
        int eDefence = hero.getLevel() * Math.max(random.nextInt(4 * type), 2 * type);
        int eHitPoints = hero.getLevel() * Math.max(random.nextInt(20 * type), 12);

        Enemy enemy = new Enemy(eAttack, eDefence, eHitPoints);
        if (fightSimulation(hero, enemy)) {
            System.out.println(StaticVariables.BORDER + "\nYou won the fight!");

            gainExp(eHitPoints * (14 + type));
            int tier = Math.min((getHeroLevel() + 5) / 5, 5);
            if (random.nextInt(3000) > (600 * tier) / type) {
                ret = 2;
            }
            System.out.println("Player hp: " + hero.getHitPoints() + " EXP: [" + hero.getExperience() + "/" + levelUp + "]");
            return ret;
        }
        return 0;
    }

    private boolean fightSimulation(Hero hero, Enemy enemy) {
        System.out.println("FIGHT VS HP: " + enemy.getHitPoints() + " A: " + enemy.getAttack() + " D: " + enemy.getDefence());
        if (random.nextInt(4) != 0) {
            while (enemy.getHitPoints() > 0) {
                enemy.setHitPoints(enemy.getHitPoints() - Math.max((hero.getAttack() - enemy.getDefence()), 1));
                if (enemy.getHitPoints() <= 0) {
                    break ;
                }
                hero.setHitPoints(hero.getHitPoints() - Math.max((enemy.getAttack() - hero.getDefence()), 1));
                if (hero.getHitPoints() <= 0) {
                    return false;
                }
            }
            return true;
        }
        while (hero.getHitPoints() > 0) {
            hero.setHitPoints(hero.getHitPoints() - Math.max((enemy.getAttack() - hero.getDefence()), 1));
            if (hero.getHitPoints() <= 0) {
                break ;
            }
            enemy.setHitPoints(enemy.getHitPoints() - Math.max((hero.getAttack() - enemy.getDefence()), 1));
            if (enemy.getHitPoints() <= 0) {
                return true;
            }
        }
        return false;
    }

    public void equip(Artifact artifact) {
        long itemId;

        if ((itemId = hero.equipGear(artifact)) != 0) {
            inventoryService.removeItem(hero.getId(), itemId);
        }
        inventoryService.addNewItem(new Inventory(hero.getId(), artifact.getId()));
    }

    public void getAllItems() {
        List<Inventory> list = inventoryService.findAllItems(hero.getId());

        if (!list.isEmpty()) {
            Inventory inventory;
            for (int i = 0; i < list.size(); i++) {
                inventory = list.get(i);
                hero.equipGear(artifactService.findById(inventory.getItemId()));
            }
        }
    }

    public HashMap<String, Artifact> getArtifacts() {
        return hero.getArtifacts();
    }
}
