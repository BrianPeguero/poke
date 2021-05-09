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
        System.out.println("");
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

class WaterPokemon extends Pokemon{

    public WaterPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }
    
}

class NormalPokemon extends Pokemon {

    public NormalPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class FirePokemon extends Pokemon {

    public FirePokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class GrassPokemon extends Pokemon {

    public GrassPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class ElectricPokemon extends Pokemon {

    public ElectricPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class IcePokemon extends Pokemon {

    public IcePokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class FightingPokemon extends Pokemon {

    public FightingPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class PoisonPokemon extends Pokemon {

    public PoisonPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class GroundPokemon extends Pokemon {

    public GroundPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class FlyingPokemon extends Pokemon {

    public FlyingPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class PsychicPokemon extends Pokemon {

    public PsychicPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class BugPokemon extends Pokemon {

    public BugPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class RockPokemon extends Pokemon {

    public RockPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class GhostPokemon extends Pokemon {

    public GhostPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class DarkPokemon extends Pokemon {

    public DarkPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class DragonPokemon extends Pokemon {

    public DragonPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class SteelPokemon extends Pokemon {

    public SteelPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}

class FairyPokemon extends Pokemon {

    public FairyPokemon(String id, String name, String[] types, HashMap<String, Integer> stats) {
        super(id, name, types, stats);
        //TODO Auto-generated constructor stub
    }

}