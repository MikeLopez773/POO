package com.collection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Collection collection = new Collection();
    private static ContactManager contactManager = new ContactManager();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("=== Collection Management System ===");
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readInt("Choose an option: ");
            
            switch (choice) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageContacts();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    viewHistory();
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Manage Items");
        System.out.println("2. Manage Contacts");
        System.out.println("3. View Statistics");
        System.out.println("4. View History");
        System.out.println("5. Exit");
    }

    private static void manageItems() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Item Management ===");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View All Items");
            System.out.println("4. View Items by Category");
            System.out.println("5. Search Item");
            System.out.println("6. Back to Main Menu");
            
            int choice = readInt("Choose an option: ");
            
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    viewAllItems();
                    break;
                case 4:
                    viewItemsByCategory();
                    break;
                case 5:
                    searchItem();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem() {
        System.out.println("\n=== Add New Item ===");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Category (e.g., Books, Coins, Action Figures, Vinyl, Cards, Games, Plants): ");
        String category = scanner.nextLine();
        
        LocalDate acquisitionDate = readDate("Acquisition Date (dd/MM/yyyy): ");
        
        double paidValue = readDouble("Paid Value: ");
        double estimatedValue = readDouble("Estimated Value: ");
        
        Item item = new Item(name, description, category, acquisitionDate, estimatedValue, paidValue);
        collection.addItem(item);
        
        System.out.println("Item added successfully!");
    }

    private static void removeItem() {
        System.out.print("\nEnter item name to remove: ");
        String name = scanner.nextLine();
        
        Item item = collection.findItemByName(name);
        if (item != null) {
            collection.removeItem(name);
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void viewAllItems() {
        System.out.println("\n=== All Items ===");
        List<Item> items = collection.getAllItems();
        
        if (items.isEmpty()) {
            System.out.println("No items in collection.");
        } else {
            for (Item item : items) {
                System.out.println(item);
                System.out.println("---");
            }
        }
    }

    private static void viewItemsByCategory() {
        System.out.println("\n=== Items by Category ===");
        Map<String, List<Item>> categories = collection.getItemsByCategories();
        
        if (categories.isEmpty()) {
            System.out.println("No items in collection.");
            return;
        }
        
        System.out.println("Available categories:");
        int index = 1;
        for (String category : categories.keySet()) {
            System.out.println(index++ + ". " + category);
        }
        
        System.out.print("\nEnter category name: ");
        String category = scanner.nextLine();
        
        List<Item> items = collection.getItemsByCategory(category);
        if (items.isEmpty()) {
            System.out.println("No items found in this category.");
        } else {
            System.out.println("\nItems in category '" + category + "':");
            for (Item item : items) {
                System.out.println(item);
                System.out.println("---");
            }
        }
    }

    private static void searchItem() {
        System.out.print("\nEnter item name to search: ");
        String name = scanner.nextLine();
        
        Item item = collection.findItemByName(name);
        if (item != null) {
            System.out.println("\n" + item);
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void manageContacts() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Contact Management ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. View All Contacts");
            System.out.println("4. View Contacts by Type");
            System.out.println("5. Search Contact");
            System.out.println("6. Back to Main Menu");
            
            int choice = readInt("Choose an option: ");
            
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    viewAllContacts();
                    break;
                case 4:
                    viewContactsByType();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addContact() {
        System.out.println("\n=== Add New Contact ===");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Type (Buyer/Seller/Trader): ");
        String type = scanner.nextLine();
        
        Contact contact = new Contact(name, email, phone, type);
        contactManager.addContact(contact);
        
        System.out.println("Contact added successfully!");
    }

    private static void removeContact() {
        System.out.print("\nEnter contact name to remove: ");
        String name = scanner.nextLine();
        
        Contact contact = contactManager.findContactByName(name);
        if (contact != null) {
            contactManager.removeContact(name);
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void viewAllContacts() {
        System.out.println("\n=== All Contacts ===");
        contactManager.displayAllContacts();
    }

    private static void viewContactsByType() {
        System.out.print("\nEnter contact type (Buyer/Seller/Trader): ");
        String type = scanner.nextLine();
        
        List<Contact> contacts = contactManager.getContactsByType(type);
        if (contacts.isEmpty()) {
            System.out.println("No contacts found with this type.");
        } else {
            System.out.println("\nContacts of type '" + type + "':");
            for (Contact contact : contacts) {
                System.out.println(contact);
                System.out.println();
            }
        }
    }

    private static void searchContact() {
        System.out.print("\nEnter contact name to search: ");
        String name = scanner.nextLine();
        
        Contact contact = contactManager.findContactByName(name);
        if (contact != null) {
            System.out.println("\n" + contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void viewStatistics() {
        collection.displayStatistics();
    }

    private static void viewHistory() {
        collection.displayHistory();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy (e.g., 23/12/2023).");
            }
        }
    }
}
