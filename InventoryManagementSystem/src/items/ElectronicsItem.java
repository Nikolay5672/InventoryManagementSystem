package items;

import java.time.LocalDate;
public class ElectronicsItem extends InventoryItem {
    public LocalDate warranty;
    public ElectronicsItem(String name, String description, String category, double price, int quantity, AutoIncrementManager manager, LocalDate warranty){
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.ID = manager.getNextID();
        this.warranty = warranty;
    }
    @Override
    public boolean isBreakable() {
        return true;
    }
    @Override
    public void breaking() {
        System.out.println("Throws the " + this.name + " against the wall!");
    }
    @Override
    public double calculateValue() {
        return quantity * price;
    }
    public LocalDate getWarranty() {
        return warranty;
    }
    public void setWarranty(LocalDate warranty) {
        this.warranty = warranty;
    }
    @Override
    public String toString() {
        return
                "ID: " + ID +
                ", name: " + name +
                ", description: " + description +
                ", category: " + category +
                ", warranty: " + warranty +
                ", price: " + price +
                ", quantity: " + quantity;
    }
}
