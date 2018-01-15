package ndr.brt.pact.cook;

public class Ingredient {
    private String name;
    private double quantity;
    private String unit;

    public String name() {
        return name;
    }

    public Ingredient name(String name) {
        this.name = name;
        return this;
    }

    public Ingredient quantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Ingredient unit(String unit) {
        this.unit = unit;
        return this;
    }
}
