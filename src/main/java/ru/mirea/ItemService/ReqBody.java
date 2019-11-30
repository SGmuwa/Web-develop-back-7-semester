package ru.mirea.ItemService;

public class ReqBody {
    private String method;
    private Item item;

    public ReqBody() {
    }

    public ReqBody(String method, Item item) {
        this.method = method;
        this.item = item;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Item getPost() {
        return item;
    }

    public void setPost(Item item) {
        this.item = item;
    }
}
