package ndr.brt.pact.cook;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Function;

public interface GetReceipe extends Function<String, Receipe> {

    Gson GSON = new Gson();

    static GetReceipe getReceipe() {
        return name -> {

            try {
                final String body = Unirest.get("http://localhost:8081/receipes/parmigiana")
                        .asString()
                        .getBody();

                return GSON.fromJson(body, Receipe.class);

            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }

        };
    }
}
