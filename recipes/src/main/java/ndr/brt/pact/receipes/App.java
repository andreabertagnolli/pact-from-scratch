package ndr.brt.pact.receipes;

import com.google.gson.Gson;
import org.apache.http.entity.ContentType;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.awt.*;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static spark.Spark.get;
import static spark.Spark.post;

public class App implements SparkApplication {

    private Receipes receipes;

    public App(Receipes receipes) {
        this.receipes = receipes;
    }

    public void init() {
        Gson gson = new Gson();

        get("/receipes/:name", (req, res) -> {
            res.type(APPLICATION_JSON.getMimeType());
            return receipes.get(req.params("name"));
        }, gson::toJson);

        post("/receipes/", (req, res) -> {
            System.out.println(req.body());
            Receipe receipe = gson.fromJson(req.body(), Receipe.class);

            receipes.insert(receipe);

            res.status(201);

            return "";
        });
    }

}
