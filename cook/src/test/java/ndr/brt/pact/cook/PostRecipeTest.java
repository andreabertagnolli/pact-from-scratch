package ndr.brt.pact.cook;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import static ndr.brt.pact.cook.PostRecipe.postRecipe;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class PostRecipeTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("recipes", "localhost", 8082, this);

    @Pact(consumer="cook")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder.given("post recipe")
                .uponReceiving("Create new recipe")
                .path("/recipes/")
                .method("POST")
                .body(new Gson().toJson(new Recipe()
                        .name("lasagne")
                        .difficulty("medium")
                        .ingredient(new Ingredient().name("ragu").quantity(1).unit("kg"))))
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactVerification
    public void post_recipe() {
        Boolean done = postRecipe().apply(new Recipe()
                .name("lasagne")
                .difficulty("medium")
                .ingredient(new Ingredient().name("ragu").quantity(1).unit("kg")));

        assertThat(done, is(true));
    }
}
