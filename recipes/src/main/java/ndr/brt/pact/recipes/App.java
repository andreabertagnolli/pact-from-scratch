package ndr.brt.pact.recipes;

import com.google.gson.Gson;
import spark.servlet.SparkApplication;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static spark.Spark.get;
import static spark.Spark.post;

public class App implements SparkApplication {

    private Recipes recipes;

    public App(Recipes recipes) {
        this.recipes = recipes;
    }

    public void init() {
        Gson gson = new Gson();

        get("/recipes/:name", (req, res) -> {
            res.type(APPLICATION_JSON.getMimeType());
            return recipes.get(req.params("name"));
        }, gson::toJson);

        post("/recipes/", (req, res) -> {
            System.out.println(req.body());
            Recipe recipe = gson.fromJson(req.body(), Recipe.class);

            recipes.insert(recipe);

            res.status(201);

            return "";
        });
    }

}
