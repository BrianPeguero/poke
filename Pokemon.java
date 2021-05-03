import java.util.HashMap;

public class Pokemon {
    String id;
    String name;
    String[] types;
    HashMap<String, Integer> stats;

    public Pokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.stats = stats;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getTypes() {
        return this.types;
    }

    public void displayTypes() {
        System.out.print("Types: ");
        for (String i : this.types) {
            System.out.print(i + " ");
        }
    }

    public HashMap<String, Integer> getStats() {
        return this.stats;
    }

    public void displayStats() {
        System.out.println("Stats");

        this.stats.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public void displayPokemon() {
        System.out.println(this.getName() + " : #" + this.getId());
        displayTypes();
        displayStats();
        System.out.println("");
    }

}
