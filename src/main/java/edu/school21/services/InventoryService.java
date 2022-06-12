package edu.school21.services;

import edu.school21.dao.InventoryDao;
import edu.school21.models.Inventory;

import java.util.List;

public class InventoryService {

    private final InventoryDao inventoryDao = new InventoryDao();

    public List<Inventory> findAllItems(long heroId) {
        return inventoryDao.findAllItems(heroId);
    }

    public void addNewItem(Inventory inventory) {
        inventoryDao.addNewItem(inventory);
    }

    public void removeItem(long heroId, long itemId) {
        inventoryDao.removeItem(heroId, itemId);
    }
}
