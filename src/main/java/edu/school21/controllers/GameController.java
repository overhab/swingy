package edu.school21.controllers;

import edu.school21.World.Player;
import edu.school21.World.WorldMap;
import edu.school21.app.InputValidation;
import edu.school21.app.StaticVariables;
import edu.school21.gui.ConsoleView;
import edu.school21.gui.Swingy;
import edu.school21.gui.ViewManager;
import edu.school21.models.*;
import edu.school21.services.ArtifactService;
import edu.school21.services.HeroService;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameController {

    private final InputValidation inputValidation;
    private final ArtifactService artifactService;
    private final HeroService heroService;
    private final Random random = new Random();
    private ViewManager viewManager;
    private UserInput userInput;
    private String input = "";
    private Swingy swingy;
    private Player player;
    private WorldMap worldMap;

    public GameController() {
        inputValidation = new InputValidation();
        heroService = new HeroService();
        artifactService = new ArtifactService();
    }

    public void start(boolean mode) {
        ConsoleView consoleView = new ConsoleView();
        swingy = new Swingy();
        userInput = new UserInput();

        userInput.setMODE(mode);
        if (!mode) {
            swingy.start();
            userInput.setSwing(swingy.getjPanel(), swingy.getLayout());
        }
        viewManager = new ViewManager(consoleView, swingy);
        viewManager.changeMode(mode);
        startGame();
        exit();
    }

    private void startGame() {

        while (true) {
            viewManager.startScreen();
            input = userInput.getInput();
            System.out.println(input);

            if (input.toUpperCase(Locale.ROOT).equals("CREATE NEW HERO")) {
                viewManager.newHero();
                newGame();
                heroService.save(player.getHero());
                viewManager.gamePlay(player);
                startTheGame();
                return;
            } else if (input.toUpperCase(Locale.ROOT).equals("SELECT HERO")) {

                List<Hero> heroes = heroService.findAll();

                if (heroes.isEmpty()) {
                    viewManager.newHero();
                    viewManager.displayWarning("No heroes");
                    newGame();
                    heroService.save(player.getHero());
                    viewManager.gamePlay(player);
                    startTheGame();
                    return;
                }

                viewManager.chooseHero(heroes);

                Hero hero;

                while (true) {
                    input = userInput.getInput();
                    hero = heroService.findByName(input);
                    if (hero == null) {
                        viewManager.displayWarning("Hero with this name doesn't exist");
                    } else {
                        break ;
                    }
                }

                player = new Player(hero, artifactService);
                player.getAllItems();
                viewManager.gamePlay(player);
                startTheGame();

                return;
            } else if (input.toUpperCase(Locale.ROOT).equals("QUIT")) {
                exit();
            }
            viewManager.wrongInput();
        }

    }

    public void newGame() {
        String classType;
        Hero hero;

        while (true) {
            input = userInput.getInput();
            if (heroService.findByName(input) != null) {
                viewManager.displayWarning("Hero with this name already exists");
            } else {
                break ;
            }
        }

        viewManager.display(StaticVariables.HERO_TYPE);

        label:
        while (true) {
            classType = userInput.getInput().toUpperCase(Locale.ROOT);
            switch (classType) {
                case "MAGE":
                    hero = new Mage(input);
                    break label;
                case "WARRIOR":
                    hero = new Warrior(input);
                    break label;
                case "ARCHER":
                    hero = new Archer(input);
                    break label;
                default:
                    viewManager.wrongInput();
                    break;
            }
        }

        if (inputValidation.HeroValidation(hero)) {
            player = new Player(hero, artifactService);
            System.out.println("Player created: " + player.getLevelUp());
        } else {
            newGame();
        }
    }

    private void startTheGame() {

        viewManager.displayLevel("Start new level" +
                "\nQuit", player);
        while (true) {
            input = userInput.getInput().toUpperCase(Locale.ROOT);
            if (input.equals("START") || input.equals("START NEW LEVEL")) {
                startLevel();
                startTheGame();
                break ;
            } else if (input.equals("QUIT")) {
                return ;
            }
            viewManager.wrongInput();
        }
    }

    private void startLevel() {
        int encounter;
        makeMap();
        worldMap.printMap();

        viewManager.refresh();

        try {
            while (true) {
                viewManager.chooseMove(player);
                input = userInput.getInput().toUpperCase(Locale.ROOT);
                if (input.equals("NORTH") || input.equals("SOUTH") || input.equals("EAST") || input.equals("WEST")) {
                    viewManager.displayMove(input.toLowerCase(Locale.ROOT));
                    encounter = worldMap.movePlayer(input);

                    if (encounter > 0) {
                        viewManager.encounter(encounter == 1 ? "GOBLIN" : "LARGE OGRE", player);
                        while (true) {
                            input = userInput.getInput().toUpperCase(Locale.ROOT);
                            if (input.equals("FIGHT") || (input.equals("RUN") && random.nextInt(1000) < 150)) {

                                if (input.equals("RUN")) {
                                    viewManager.display("You can't run!");
                                }

                                int drop;

                                if ((drop = player.encounter(encounter)) == 0) {
                                    viewManager.displayEnd("YOU DIED! ! !");
                                    return;
                                }

                                if (drop == 2) {
                                    Artifact artifact = artifactService.findByTier(Math.min((player.getHeroLevel() + 5) / 5, 5));
                                    viewManager.afterEncounter(player, drop, artifact.getName());
                                    receiveLoot(artifact);
                                    heroService.update(player.getHero());
                                } else {
                                    heroService.update(player.getHero());
                                    viewManager.afterEncounter(player, drop, null);
                                }

                                if (player.isNextLevel()) {
                                    player.setNextLevel(false);
                                    viewManager.levelUp();
                                }

                                break ;
                            } else if (input.equals("RUN")) {
                                break ;
                            } else {
                                viewManager.wrongInput();
                            }
                        }
                    }

                    worldMap.printMap();
                } else if (input.equals("QUIT")) {
                    break ;
                } else {
                    viewManager.wrongInput();
                }
            }
        } catch (IndexOutOfBoundsException exception) {
            heroService.update(player.getHero());
            viewManager.displayEnd("YOU WIN! ! !");
        }
    }

    private void receiveLoot(Artifact artifact) {
        while (true) {
            input = userInput.getInput().toUpperCase(Locale.ROOT);
            if (input.equals("TAKE IT") || input.equals("TAKE")) {
                player.equip(artifact);
                break ;
            } else if (input.equals("LEAVE IT") || input.equals("LEAVE")) {
                return;
            } else {
                viewManager.wrongInput();
            }
        }
    }

    public void makeMap() {
        int mapSize = (player.getHeroLevel() - 1) * 5 + 10 - 1;
        worldMap = new WorldMap(mapSize);
        worldMap.initMap(player.getHeroLevel());
    }

    public void exit() {
        System.out.println("exit!");
        swingy.exit();
        userInput.close();
    }
}
