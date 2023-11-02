package items;

import java.time.LocalDate;
public class GroceryItem extends InventoryItem {
    public LocalDate perishDate;
    public GroceryItem(String name, String description, String category, double price, int quantity, AutoIncrementManager manager, LocalDate perishDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.ID = manager.getNextID();
        this.perishDate = perishDate;
    }
    @Override
    public double calculateValue() {
        return price * quantity * 0.8;
    }
    @Override
    public boolean isPerishable() {
        return true;
    }
    @Override
    public void perishing() {
        System.out.println("Too old! Throw it away!");
    }
    public LocalDate getPerishDate() {
        return perishDate;
    }
    public void setPerishDate(LocalDate perishDate) {
        this.perishDate = perishDate;
    }
    @Override
    public String toString() {
        return "ID: " + ID +
                ", name: " + name +
                ", description: " + description +
                ", category: " + category +
                ", perish date: " + perishDate +
                ", price: " + price +
                ", quantity: " + quantity;

    }
}
