package edu.school21.app;

import edu.school21.controllers.GameController;
import edu.school21.dao.InventoryDao;
import edu.school21.models.Inventory;
import edu.school21.services.ArtifactService;

import java.util.HashMap;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        HibernateSessionFactoryUtil.getSessionFactory();

        GameController game = new GameController();

        game.start(false);

        /*HashMap<String, Integer> map = new HashMap<>(3);

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        for (String key : map.keySet()) {
            System.out.println(key);
        }*/
    }
}
