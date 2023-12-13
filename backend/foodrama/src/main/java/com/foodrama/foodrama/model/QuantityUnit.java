package com.foodrama.foodrama.model;

public enum QuantityUnit {
	ML("ml"),
	L("l"),
	G("g"),
    KG("kg"),
    TSP("tsp"),
    TBSP("tbsp"),
    CUP("cup"),
    PINCH("pinch");

    private final String label;

    QuantityUnit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
 // Static method to get QuantityUnit by label
    public static QuantityUnit fromLabel(String label) {
        for (QuantityUnit unit : QuantityUnit.values()) {
            if (unit.label.equalsIgnoreCase(label)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No QuantityUnit with label: " + label);
    }
}