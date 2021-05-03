# ![Gif of bulbasaur](https://projectpokemon.org/images/normal-sprite/bulbasaur.gif) Poke ![Gif of Squirtle](https://projectpokemon.org/images/normal-sprite/squirtle.gif)

Poke is a pokedex made in java that is able to search for pokemon using the PokeAPI. A trainer is able to log in, search for a pokemon, add it to your team, remove from team, and display current team.

## Installation

I used the org.JSON library in order to parse JSON data.

The .jar file is inn the project but to install and use it you need to go to 

```
1. Right-click the Java project to which you want to add a library, and select Properties from the menu.
2. Select Java Build Path, and click the Libraries tab.
```
![img for how to install library](https://resources.cloud.genuitec.com/wp-content/uploads/2014/07/build_path_3.gif)
```
3. Click the Add Library button, and choose the appropriate Java EE Library.
4. Click Next to view the library contents, and click Finish.
```]

## Usage

```import org.json.JSONObject;

public class Poke {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject(content.toString());
        String name = obj.getString("name");
    }
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.