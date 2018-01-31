package ndr.brt.pact.recipes;

public interface Recipes {

    Recipe get(String name);

    int insert(Recipe recipe);
}
