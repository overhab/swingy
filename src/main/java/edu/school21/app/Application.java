package edu.school21.app;

import edu.school21.controllers.GameController;

public class Application {
    public static void main(String[] args) {
        HibernateSessionFactoryUtil.getSessionFactory();

        GameController game = new GameController();

        game.start(false);
    }
}
