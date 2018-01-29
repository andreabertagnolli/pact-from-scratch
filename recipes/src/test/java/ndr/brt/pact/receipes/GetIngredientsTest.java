package ndr.brt.pact.receipes;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static ndr.brt.pact.receipes.IngredientNamed.ingredientNamed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class GetIngredientsTest {

    @Rule public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("ingredients", "localhost", 8081, this);

    @Pact(consumer="receipes")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder.given("get ingredients")
                .uponReceiving("Get all available ingredients")
                .path("/ingredients/")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new Gson().toJson(asList(
                        new Ingredient("wheat flour").allergen("gluten"),
                        new Ingredient("potato").category("tuber")
                    )
                ))
                .toPact();
    }

    @Test
    @PactVerification
    public void get_all_the_ingredients() {
        final List<Ingredient> ingredients = GetIngredients.ingredients().get();

        assertThat(ingredients, hasSize(2));
        assertThat(ingredients, hasItem(ingredientNamed("wheat flour")));
        assertThat(ingredients, hasItem(ingredientNamed("potato")));
    }
}
