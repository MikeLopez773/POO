package com.collection;

import java.time.LocalDate;

public class Item {
    private String name;
    private String description;
    private String category;
    private LocalDate acquisitionDate;
    private double estimatedValue;
    private double paidValue;

    public Item(String name, String description, String category, LocalDate acquisitionDate, 
                double estimatedValue, double paidValue) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.acquisitionDate = acquisitionDate;
        this.estimatedValue = estimatedValue;
        this.paidValue = paidValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(double paidValue) {
        this.paidValue = paidValue;
    }

    @Override
    public String toString() {
        return String.format("Item: %s\n  Category: %s\n  Description: %s\n  Acquisition Date: %s\n  Paid Value: %.2f\n  Estimated Value: %.2f",
                name, category, description, acquisitionDate, paidValue, estimatedValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.estimatedValue, estimatedValue) == 0 &&
                Double.compare(item.paidValue, paidValue) == 0 &&
                name.equals(item.name) &&
                description.equals(item.description) &&
                category.equals(item.category) &&
                acquisitionDate.equals(item.acquisitionDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + acquisitionDate.hashCode();
        long temp = Double.doubleToLongBits(estimatedValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(paidValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
