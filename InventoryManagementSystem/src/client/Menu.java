package client;

import files.FileManagement;
import items.*;
import paymentsAndOrders.Order;
import paymentsAndOrders.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    public static void start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1.View available items");
        System.out.println("2.Add an item");
        System.out.println("3.Remove an item");
        System.out.println("4.View order");
        System.out.println("5.Place an order");
        System.out.println("6.Exit");
        String input = sc.nextLine();
        while(true){
            switch (input) {
                case "1" ->{
                    for(InventoryItem item : FileManagement.readAllItems()){
                        System.out.println("ID:" + item.ID + " " + item.name + " " + item.getPrice() + "$ x" + item.quantity);
                    }

                    System.out.println("1.Return");
                    System.out.println("2.Exit");
                    String command = sc.nextLine();
                    switch (command){
                        case "1" -> start();
                        case "2" -> {
                            return;
                        }
                    }
                }
                case "2" -> {
                    addItem(sc);
                    System.out.println("1.Return");
                    System.out.println("2.Exit");
                    String command = sc.nextLine();
                    switch (command){
                        case "1" -> start();
                        case "2" -> {
                            return;
                        }
                    }
                }
                case "3" ->{
                    removeItem(sc);
                    System.out.println("1.Return");
                    System.out.println("2.Exit");
                    String command = sc.nextLine();
                    switch (command){
                        case "1" -> start();
                        case "2" -> {
                            return;
                        }
                    }
                }
                case "4" -> {
                    System.out.println("Enter order id:");
                    int id = Integer.parseInt(sc.nextLine());
                    viewOrderById(id);
                    System.out.println("1.Return");
                    System.out.println("2.Exit");
                    String command = sc.nextLine();
                    switch (command) {
                        case "1" -> start();
                        case "2" -> {
                            return;
                        }
                    }
                }
                case "5" -> {
                    addOrder(sc);
                    System.out.println("1.Return");
                    System.out.println("2.Exit");
                    String command = sc.nextLine();
                    switch (command) {
                        case "1" -> start();
                        case "2" -> {
                            return;
                        }
                    }
                }
                case "6" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid command!");
                    start();
                }
            }
        }
    }

    private static void removeItem(Scanner sc){
        System.out.println("Enter the ID of the item you want to remove:");
        int ID = Integer.parseInt(sc.nextLine());
        List<Integer> ids = new ArrayList<>();
        for(InventoryItem item : FileManagement.readAllItems()){
            ids.add(item.getID());
        }
        if(ids.contains(ID)){
            FileManagement.deleteItem(ID);
        }
        else {
            System.out.println("No such id!");
        }
    }

    private static void addItem(Scanner sc){
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Description:");
        String description = sc.nextLine();

        double price;
        while(true){
            System.out.println("Price:");
            price = Double.parseDouble(sc.nextLine());
            if(price >= 0){
                break;
            }else {
                System.out.println("Price must be at least zero!");

            }
        }

        int quantity;
        while(true){
            System.out.println("Quantity:");
            quantity = Integer.parseInt(sc.nextLine());
            if(quantity >= 0){
                break;
            }else {
                System.out.println("Quantity must be at least zero!");

            }
        }
        while(true) {
            System.out.println("Choose a category(electronics|groceries|fragile):");
            String category = sc.nextLine();
            AutoIncrementManager managerItem = new AutoIncrementManager("C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\last_item_id.txt", 1000);
            switch (category) {
                case "electronics" -> {
                    System.out.println("Warranty(in months):");
                    int months = Integer.parseInt(sc.nextLine());
                    LocalDate now = LocalDate.now();
                    LocalDate warranty = now.plusMonths(months);
                    ElectronicsItem item = new ElectronicsItem(name, description, category, price, quantity, managerItem, warranty);
                    FileManagement.add(item);
                    return;
                }
                case "groceries" -> {
                    System.out.println("Perish date(in days):");
                    int days = Integer.parseInt(sc.nextLine());
                    LocalDate now = LocalDate.now();
                    LocalDate perishDate = now.plusDays(days);
                    GroceryItem item = new GroceryItem(name, description, category, price, quantity, managerItem, perishDate);
                    FileManagement.add(item);
                    return;
                }
                case "fragile" -> {
                    System.out.println("Weight:");
                    double weight = Double.parseDouble(sc.nextLine());
                    FragileItem item = new FragileItem(name, description, category, price, quantity, managerItem, weight);
                    FileManagement.add(item);
                    return;
                }
                default -> System.out.println("No such category!");
            }
        }
    }

    private static void addOrder(Scanner sc){
        List<InventoryItem> order = new ArrayList<>();
        System.out.println("Available items:");
        for(InventoryItem inventoryItem : FileManagement.readAllItems()){
            System.out.println(inventoryItem);
        }
        System.out.println("Add items and finish the order with end!");
        int quantity = 0;
        while(true){
            System.out.println("Product name:");
            String itemName = sc.nextLine();

            if(itemName.equals("end")){
                break;
            }

            if(FileManagement.findByName(itemName) != null){
                InventoryItem newItem = FileManagement.findByName(itemName);
                assert newItem != null;
                System.out.println("Enter quantity");
                quantity = Integer.parseInt(sc.nextLine());
                if(newItem.getQuantity() < quantity){
                    System.out.println("Not enough amount!");
                }
                else if(quantity < 1){
                    System.out.println("Must be over zero!");

                }
                else {
                    FileManagement.modifyItemQuantity(newItem.getName(), newItem.getQuantity() - quantity);
                    newItem.setQuantity(quantity);
                    order.add(newItem);
                }
            }
        }

        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter address:");
        String address = sc.nextLine();

        double total = 0;
        for(InventoryItem item : order){
            total += item.getPrice() * quantity;
        }

        AutoIncrementManager managerOrder = new AutoIncrementManager("C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\last_order_id.txt", 100);
        Order newOrder = new Order(managerOrder, name, total, address, order);
        FileManagement.add(newOrder);
        System.out.println("Your total is: " + total);


        System.out.println("1.Pay by cash");
        System.out.println("2.Pay with card");
        System.out.println("3.Cancel order");
        int command = Integer.parseInt(sc.nextLine());
        AutoIncrementManager managerPayment = new AutoIncrementManager("C:\\Users\\PC18\\Desktop\\Sirma Academy\\InventoryManagementSystem\\src\\files\\last_payment_id.txt", 100);
        switch (command){
            case 1 ->{
                Payment payment = new Payment(managerPayment, total, name, "cash");
                FileManagement.add(payment);
                payment.payByCash();
                System.out.println("Your order with id:" + newOrder.getId() + "has been successful!");/////////
            }
            case 2 -> {
                Payment payment = new Payment(managerPayment, total, name, "card");
                FileManagement.add(payment);
                payment.payByCard();
                System.out.println("Your order with id:" + newOrder.getId() + " has been successful!");///////
            }
            case 3 -> FileManagement.deleteOrder(newOrder.getId());
        }

    }

    private static void viewOrderById(int id){
        boolean found = false;
        for(Order order : FileManagement.readAllOrders()){
            if(Objects.requireNonNull(FileManagement.findById(order.getId())).getId() == id){
                System.out.println(order);
                found = true;
            }

        }

        if(!found){
            System.out.println("Order not found!");

        }
    }

}
