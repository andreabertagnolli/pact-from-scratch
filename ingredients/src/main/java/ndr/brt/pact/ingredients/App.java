package ndr.brt.pact.ingredients;

import com.google.gson.Gson;
import spark.servlet.SparkApplication;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static spark.Spark.get;

public class App implements SparkApplication {

    private Ingredients ingredients;

    public App(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public void init() {
        Gson gson = new Gson();

        get("/ingredients/", (req, res) -> {
            res.type(APPLICATION_JSON.getMimeType());
            return ingredients.get();
        }, gson::toJson);
    }

}
