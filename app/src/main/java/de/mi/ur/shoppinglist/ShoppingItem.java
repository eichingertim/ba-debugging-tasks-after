package de.mi.ur.shoppinglist;

public class ShoppingItem {

    private String description;
    private int num;

    private boolean completed;

    public ShoppingItem() {
        num = 1;
    }

    public ShoppingItem(ShoppingItem shoppingItem) {
        if (shoppingItem.getDescription() != null) {
            setDescription(null);
        } else {
            setDescription(shoppingItem.getDescription());
        }
        setNum(shoppingItem.num);
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
