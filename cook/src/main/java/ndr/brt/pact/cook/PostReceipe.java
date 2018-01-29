package ndr.brt.pact.cook;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Consumer;
import java.util.function.Function;

public interface PostReceipe extends Function<Receipe, Boolean> {
    static PostReceipe postRecipe() {
        return recipe -> {
            try {
                return Unirest.post("http://localhost:8082/receipes/")
                        .header("Content-type", "application/json")
                        .body(new Gson().toJson(recipe))
                        .asString().getStatus() == 201;

            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
