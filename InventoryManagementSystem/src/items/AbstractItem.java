package items;

import interfaces.*;

import java.io.Serializable;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable , Serializable {
    public String name;
    public String description;
    public String category;
    public double price;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void showDescription() {
        System.out.println(description);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String getCategory() {
        return this.category;
    }
    @Override
    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public boolean isBreakable() {
        return false;
    }
    @Override
    public void breaking(){
        System.out.println("Cannot break!");
    }
    @Override
    public void getInfo() {
        System.out.println(this);
    }
    @Override
    public boolean isPerishable() {
        return false;
    }
    @Override
    public void perishing() {
        System.out.println("Cannot perish!");
    }
}
