package ndr.brt.pact.cook;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Function;

public interface PostRecipe extends Function<Recipe, Boolean> {
    static PostRecipe postRecipe() {
        return recipe -> {
            try {
                return Unirest.post("http://localhost:8082/recipes/")
                        .header("Content-type", "application/json")
                        .body(new Gson().toJson(recipe))
                        .asString().getStatus() == 201;

            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
