package ru.mirea.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDbConnection itConnect;
    @Autowired
    public ItemService(ItemDbConnection itConnect){
        this.itConnect = itConnect;
    }
    public List<Item> geItems() {
        return itConnect.geItems();
    }
    public void deleteItem(int id) {
        itConnect.deleteItem(id);
    }
    public void putItem(String name, String type, Integer count, double price) {
        itConnect.putItem(
                (String) name,
                (String) type,
                (Integer) count,
                price);
    }
    public String greet() {
        return "Hello, Mock";
    }
    public Item getById(int id) {
        return itConnect.getById(id);
    }
}
