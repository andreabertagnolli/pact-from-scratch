package ndr.brt.pact.cook;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Function;

public interface GetRecipe extends Function<String, Recipe> {

    Gson GSON = new Gson();

    static GetRecipe getRecipe() {
        return name -> {

            try {
                final String body = Unirest.get("http://localhost:8081/recipes/" + name)
                        .asString()
                        .getBody();

                return GSON.fromJson(body, Recipe.class);

            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }

        };
    }
}
