package ndr.brt.pact.cook;

import java.util.function.Function;

public class Cook implements Function<String, Dish> {

    private final GetRecipe getRecipe;

    public Cook(GetRecipe getRecipe) {
        this.getRecipe = getRecipe;
    }

    @Override
    public Dish apply(String s) {
        getRecipe.apply(s);
        return new Dish(s);

    }
}
