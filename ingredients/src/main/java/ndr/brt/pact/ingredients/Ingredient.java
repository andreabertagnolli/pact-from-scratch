package ndr.brt.pact.ingredients;

import java.util.List;

public class Ingredient {

    private final String name;
    private final List<String> allergens;
    private final double quantity = 0.0;
    private String category;

    public Ingredient(String name, List<String> allergens) {
        this.name = name;
        this.allergens = allergens;
    }

    public Ingredient category(String category) {
        this.category = category;
        return this;
    }
}
