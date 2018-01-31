package ndr.brt.pact.cook;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String difficulty;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe name(String name) {
        this.name = name;
        return this;
    }

    public Recipe difficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Recipe ingredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    public String name() {
        return name;
    }

    public List<Ingredient> ingredients() {
        return this.ingredients;
    }
}
