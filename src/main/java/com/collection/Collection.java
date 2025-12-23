package com.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Collection {
    private List<Item> items;
    private List<String> history;

    public Collection() {
        this.items = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        history.add("Added item: " + item.getName() + " on " + java.time.LocalDate.now());
    }

    public void removeItem(String name) {
        Item item = findItemByName(name);
        if (item != null) {
            items.remove(item);
            history.add("Removed item: " + name + " on " + java.time.LocalDate.now());
        }
    }

    public Item findItemByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Item> getItemsByCategory(String category) {
        return items.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public Map<String, List<Item>> getItemsByCategories() {
        Map<String, List<Item>> categorized = new HashMap<>();
        for (Item item : items) {
            categorized.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
        }
        return categorized;
    }

    public double getTotalPaidValue() {
        return items.stream().mapToDouble(Item::getPaidValue).sum();
    }

    public double getTotalEstimatedValue() {
        return items.stream().mapToDouble(Item::getEstimatedValue).sum();
    }

    public int getTotalItems() {
        return items.size();
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public void displayStatistics() {
        System.out.println("\n=== Collection Statistics ===");
        System.out.println("Total Items: " + getTotalItems());
        System.out.println("Total Paid Value: " + String.format("%.2f", getTotalPaidValue()));
        System.out.println("Total Estimated Value: " + String.format("%.2f", getTotalEstimatedValue()));
        System.out.println("Potential Profit: " + String.format("%.2f", getTotalEstimatedValue() - getTotalPaidValue()));
        
        System.out.println("\nItems by Category:");
        Map<String, List<Item>> categories = getItemsByCategories();
        for (Map.Entry<String, List<Item>> entry : categories.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue().size() + " items");
        }
    }

    public void displayHistory() {
        System.out.println("\n=== Collection History ===");
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            for (String entry : history) {
                System.out.println("  " + entry);
            }
        }
    }
}
