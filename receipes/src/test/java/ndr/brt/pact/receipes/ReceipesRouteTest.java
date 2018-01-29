package ndr.brt.pact.receipes;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import spark.Spark;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PactRunner.class)
@Provider("receipes")
@PactBroker(host = "localhost", port = "1312")
public class ReceipesRouteTest {

    private static final int PORT = 65432;
    private Receipes receipes = mock(Receipes.class);

    @BeforeClass
    public static void setup() {
        Spark.port(PORT);
    }

    @TestTarget public final Target target = new HttpTarget(PORT);

    @State("get receipe")
    public void get_receipe() {
        when(receipes.get("parmigiana"))
                .thenReturn(new Receipe("parmigiana", "easy", singletonList(
                        new Ingredient("eggplant").quantity(1.0, "pcs")
                )));

        final App app = new App(receipes);
        app.init();
    }

    @State("post receipe")
    public void post_receipe() {
        final App app = new App(receipes);
        app.init();
    }
}
