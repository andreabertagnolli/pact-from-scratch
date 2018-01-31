package ndr.brt.pact.cook;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CookTest {

    private GetRecipe getRecipe = mock(GetRecipe.class);

    @Test
    public void prepare_a_dish() {
        final Cook cook = new Cook(getRecipe);

        final Dish dish = cook.apply("pizza");

        assertThat(dish.name(), is("pizza"));
        verify(getRecipe).apply("pizza");
    }

}