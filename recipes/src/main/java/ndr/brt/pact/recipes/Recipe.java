package ndr.brt.pact.recipes;

import java.util.List;

public class Recipe {

    private final String name;
    private final String difficulty;
    private final List<Object> ingredients;
    private String author;

    public Recipe(String name, String difficulty, List<Object> ingredients) {
        this.name = name;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
    }
}
