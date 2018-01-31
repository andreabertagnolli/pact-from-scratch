package ndr.brt.pact.recipes;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import spark.Spark;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PactRunner.class)
@Provider("recipes")
@PactBroker(host = "localhost", port = "1312")
public class RecipesRouteTest {

    private static final int PORT = 65432;
    private Recipes recipes = mock(Recipes.class);

    @BeforeClass
    public static void setup() {
        Spark.port(PORT);
    }

    @TestTarget public final Target target = new HttpTarget(PORT);

    @State("get recipe")
    public void get_recipe() {
        when(recipes.get("parmigiana"))
                .thenReturn(new Recipe("parmigiana", "easy", singletonList(
                        new Ingredient("eggplant").quantity(1.0, "pcs")
                )));

        final App app = new App(recipes);
        app.init();
    }

    @State("post recipe")
    public void post_recipe() {
        final App app = new App(recipes);
        app.init();
    }
}
