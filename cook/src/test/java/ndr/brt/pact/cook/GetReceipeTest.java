package ndr.brt.pact.cook;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.gson.Gson;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Rule;
import org.junit.Test;

import static ndr.brt.pact.cook.GetReceipe.getReceipe;
import static ndr.brt.pact.cook.IngredientNamed.ingredientNamed;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class GetReceipeTest {

    @Rule public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("receipes", "localhost", 8081, this);

    @Pact(consumer="cook")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder.given("get receipe")
                .uponReceiving("Get a receipe by name")
                .path("/receipes/parmigiana")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new Gson().toJson(new Receipe()
                        .name("parmigiana")
                        .difficulty("easy")
                        .ingredient(new Ingredient().name("eggplant").quantity(1).unit("pcs"))
                ))
                .toPact();
    }

    @Test
    @PactVerification
    public void get_receipe_by_name() {
        final Receipe receipe = getReceipe().apply("parmigiana");

        assertThat(receipe.name(), is("parmigiana"));
        assertThat(receipe.ingredients(), hasItem(ingredientNamed("eggplant")));
    }
}