package ru.mirea.ItemService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDbConnection itConnect;

    public ItemService(ItemDbConnection itConnect) {
        this.itConnect = itConnect;
    }

    List<Item> geItems() {
        return itConnect.geItems();
    }

    void deleteItem(int id) {
        itConnect.deleteItem(id);
    }

    void putItem(String name, String type, Integer count, double price) {
        itConnect.putItem(
                name,
                type,
                count,
                price);
    }

    String greet() {
        return "Greetings!";
    }

    Item getById(int id) {
        return itConnect.getById(id);
    }
}
