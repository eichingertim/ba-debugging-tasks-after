package de.mi.ur.shoppinglist;

public class ShoppingList {

    private String description;
    private int num;

    private boolean completed;

    public ShoppingList() {
        num = 1;
    }

    public ShoppingList(ShoppingList shoppingList) {
        setDescription(shoppingList.getDescription());
        setNum(shoppingList.getNum());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
