package ndr.brt.pact.ingredients;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.runner.RunWith;
import spark.Spark;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PactRunner.class)
@Provider("ingredients")
@PactBroker(host = "localhost", port = "1312")
public class IngredientsRouteTest {

    private static final int PORT = 65432;
    private Ingredients ingredients = mock(Ingredients.class);

    @Before
    public void setup() {
        Spark.port(PORT);
        final App app = new App(ingredients);
        app.init();
    }

    @TestTarget public final Target target = new HttpTarget(PORT);

    @State("get ingredients")
    public void get_recipe() {
        when(ingredients.get())
                .thenReturn(asList(
                        new Ingredient("wheat flour", singletonList("gluten")),
                        new Ingredient("potato", emptyList()).category("tuber")
                ));
    }
}
