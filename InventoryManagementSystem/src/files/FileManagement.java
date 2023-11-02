package files;

import items.InventoryItem;
import paymentsAndOrders.Order;
import paymentsAndOrders.Payment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    public static void add(InventoryItem item) {

        if(findByName(item.getName()) != null){
            System.out.println("Item with that name already exists!");
        }
        else{
            String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\listedItems.txt";
            List<InventoryItem> items = new ArrayList<>(readAllItems());
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                items.add(item);
                for (InventoryItem inventoryItem : items) {
                    oos.writeObject(inventoryItem);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<InventoryItem> readAllItems() {

        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\listedItems.txt";
        List<InventoryItem> items = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    InventoryItem inventoryItem = (InventoryItem) ois.readObject();
                    items.add(inventoryItem);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or is empty
        }

        return items;
    }

    public static void deleteItem(int id){
        List<InventoryItem> items = readAllItems();
        items.removeIf(item -> item.ID == id);

        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\listedItems.txt";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            for(InventoryItem item : items){
                oos.writeObject(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static InventoryItem findByName(String name){
        List<InventoryItem> items = readAllItems();
        for(InventoryItem item : items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public static void add(Order order) {

        if(findById(order.getId()) != null){
            System.out.println("Order with that id already exists!");
        }
        else{
            String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\orders.txt";
            List<Order> orders = new ArrayList<>(readAllOrders());
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                orders.add(order);
                for (Order order1 : orders) {
                    oos.writeObject(order1);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Order> readAllOrders() {

        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\orders.txt";
        List<Order> orders = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Order order = (Order) ois.readObject();
                    orders.add(order);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or is empty
        }
        return orders;
    }

    public static Order findById(int id){
        List<Order> orders = readAllOrders();
        for(Order order : orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public static void modifyItemQuantity(String name, int quantity){
        /////////////////////////////
        /////////////////////////////
        InventoryItem modifiedItem = findByName(name);
        assert modifiedItem != null;
        modifiedItem.setQuantity(quantity);
        int id = modifiedItem.getID();
        deleteItem(id);
        add(modifiedItem);
    }

    public static void deleteOrder(int id){
        List<Order> orders = readAllOrders();
        orders.removeIf(item -> item.getId() == id);
        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\orders.txt";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            for(Order order : orders){
                oos.writeObject(order);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Order cancelled successfully!");
    }

    public static void add(Payment payment) {

//        if(findById(payment.getId()) != null){  ////////////////////
//            System.out.println("Payment with that id already exists!");
//        }

        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\payments.txt";
        List<Payment> payments = new ArrayList<>(readAllPayments());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            payments.add(payment);
            for (Payment payment1 : payments) {
                    oos.writeObject(payment1);
            }

            System.out.println("Payment made successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Payment> readAllPayments() {

        String filePath = "C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\payments.txt";
        List<Payment> payments = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Payment payment = (Payment) ois.readObject();
                    payments.add(payment);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or is empty
        }
        return payments;
    }
}
