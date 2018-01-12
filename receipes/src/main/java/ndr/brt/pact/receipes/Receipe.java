package ndr.brt.pact.receipes;

import java.util.List;

public class Receipe {

    private final String name;
    private final String difficulty;
    private final List<Object> ingredients;

    public Receipe(String name, String difficulty, List<Object> ingredients) {
        this.name = name;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
    }
}
