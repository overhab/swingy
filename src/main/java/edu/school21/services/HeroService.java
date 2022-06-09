package edu.school21.services;

import edu.school21.dao.HeroDAO;
import edu.school21.models.Hero;

import java.util.List;
import java.util.Optional;

public class HeroService {
    private final HeroDAO heroDAO = new HeroDAO();

    public HeroService() {
    }

    public Optional<Hero> findById(long id) {
        return heroDAO.findById(id);
    }

    public Hero findByName(String name) {
        return heroDAO.findByName(name);
    }

    public void save(Hero hero) {
        heroDAO.save(hero);
    }

    public void update(Hero hero) {
        heroDAO.update(hero);
    }

    public void delete(Hero hero) {
        heroDAO.delete(hero);
    }

    public List<Hero> findAll() {
        return heroDAO.findAll();
    }
}
