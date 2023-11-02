package items;

public class FragileItem extends InventoryItem {
    double weight;
    public FragileItem(String name, String description, String category, double price, int quantity, AutoIncrementManager manager, double weight){
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.ID = manager.getNextID();
        this.weight = weight;
    }
    @Override
    public boolean isBreakable() {
        return true;
    }
    @Override
    public void breaking() {
        System.out.println("You should've been more careful!");
    }
    @Override
    public double calculateValue() {
        return quantity * price * 1.2;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "ID: " + ID +
                ", name: " + name +
                ", description: " + description +
                ", category: " + category +
                ", weight: " + weight +
                ", price: " + price +
                ", quantity: " + quantity;
    }
}
