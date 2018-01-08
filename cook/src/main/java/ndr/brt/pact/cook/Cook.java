package ndr.brt.pact.cook;

import java.util.function.Function;

public class Cook implements Function<String, Dish> {

    private final GetReceipe getReceipe;

    public Cook(GetReceipe getReceipe) {
        this.getReceipe = getReceipe;
    }

    @Override
    public Dish apply(String s) {
        getReceipe.apply(s);
        return new Dish(s);

    }
}
