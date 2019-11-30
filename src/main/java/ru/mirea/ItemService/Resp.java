package ru.mirea.ItemService;

import java.util.List;

public class Resp {
    private String method;
    private boolean status;
    private List<Item> item;

    public Resp(String method, boolean status, List<Item> item) {
        this.method = method;
        this.status = status;
        this.item = item;
    }

    public Resp() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
