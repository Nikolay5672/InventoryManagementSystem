package paymentsAndOrders;

import items.AutoIncrementManager;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private double total;
    private String name;
    private String paymentMethod;

    public Payment(AutoIncrementManager manager, double total, String name, String paymentMethod) {
        this.id = manager.getNextID();
        this.total = total;
        this.name = name;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", total: " + total +
                ", name='" + name +
                ", paymentMethod: " + paymentMethod;
    }

    public void payByCard() {
        System.out.println("Paying by card!");
    }

    public void payByCash(){
        System.out.println("Paying with cash!");

    }
}