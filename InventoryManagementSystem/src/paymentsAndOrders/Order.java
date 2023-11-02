package paymentsAndOrders;

import items.AutoIncrementManager;
import items.InventoryItem;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private String name;
    private double total;
    private String address;
    private List<InventoryItem> order;

    public Order(AutoIncrementManager manager, String name, double total, String address, List<InventoryItem> order){
        this.id = manager.getNextID();
        this.name = name;
        this.total = total;
        this.address = address;
        this.order = order;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InventoryItem> getOrder() {
        return order;
    }

    public void setOrder(List<InventoryItem> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " Customer name: " + name +
                " Total: " + total +
                " Address: " + address +
                " Order: " + order;
    }
}
