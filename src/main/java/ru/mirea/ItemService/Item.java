package ru.mirea.ItemService;

class Item {
    private int id;
    private String name;
    private String type;
    private int count;
    private double price;


    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    Item(int id, String name, String type, int count, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.count = count;
        this.price = price;
    }

    Item(String name, String type, int count, double price) {
        this.name = name;
        this.type = type;
        this.count = count;
        this.price = price;
    }

    int getId() {
        return id;
    }

    String getType() {
        return type;
    }

    int getCount() {
        return count;
    }

    String getName() {
        return name;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setType(String type) {
        this.type = type;
    }

    void setCount(int count) {
        this.count = count;
    }
}
