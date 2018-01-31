package ndr.brt.pact.recipes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Supplier;

public interface GetIngredients extends Supplier<List<Ingredient>> {

    Gson GSON = new Gson();
    Type INGREDIENTS_TYPE = new TypeToken<List<Ingredient>>() {}.getType();

    static GetIngredients ingredients() {
        return () -> {

            try {
                final String body = Unirest.get("http://localhost:8081/ingredients/")
                        .asString()
                        .getBody();

                return GSON.fromJson(body, INGREDIENTS_TYPE);

            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }

        };
    }

}
