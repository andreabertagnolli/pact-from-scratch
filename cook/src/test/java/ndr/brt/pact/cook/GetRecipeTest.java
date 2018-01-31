package ndr.brt.pact.cook;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import static ndr.brt.pact.cook.GetRecipe.getRecipe;
import static ndr.brt.pact.cook.IngredientNamed.ingredientNamed;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class GetRecipeTest {

    @Rule public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("recipes", "localhost", 8081, this);

    @Pact(consumer="cook")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder.given("get recipe")
                .uponReceiving("Get a recipe by name")
                .path("/recipes/parmigiana")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new Gson().toJson(new Recipe()
                        .name("parmigiana")
                        .difficulty("easy")
                        .ingredient(new Ingredient().name("eggplant").quantity(1).unit("pcs"))
                ))
                .toPact();
    }

    @Test
    @PactVerification
    public void get_recipe_by_name() {
        final Recipe recipe = getRecipe().apply("parmigiana");

        assertThat(recipe.name(), is("parmigiana"));
        assertThat(recipe.ingredients(), hasItem(ingredientNamed("eggplant")));
    }
}