package ndr.brt.pact.recipes;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private final String name;
    private double quantity;
    private String unit;
    private List<String> allergens = new ArrayList<>();
    private String category;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient quantity(double quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
        return this;
    }

    public Ingredient allergen(String allergen) {
        allergens.add(allergen);
        return this;
    }

    public Ingredient category(String category) {
        this.category = category;
        return this;
    }

    public String name() {
        return name;
    }

}
