package ndr.brt.pact.receipes;

public interface Receipes {

    Receipe get(String name);

    int insert(Receipe receipe);
}