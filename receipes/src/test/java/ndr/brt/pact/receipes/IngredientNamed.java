package ndr.brt.pact.receipes;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IngredientNamed extends BaseMatcher<Ingredient> {

    private String name;

    public static IngredientNamed ingredientNamed(String name) {
        return new IngredientNamed(name);
    }

    private IngredientNamed(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Object o) {
        return name.equals(Ingredient.class.cast(o).name());
    }

    @Override
    public void describeTo(Description description) {

    }
}
