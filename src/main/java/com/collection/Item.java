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
}
