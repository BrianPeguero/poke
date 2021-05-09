import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class Trainer {
    private int id;
    private String username;
    private String password;
    ArrayList<Pokemon> team;

    public Trainer() {

    }

    public Trainer (int id, String username, String password) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.team = fillTeam();
    }

    public Trainer(int id) {
        this.id = id;
        this.team = new ArrayList<Pokemon>();
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String password() {
        return this.password;
    }


    public void displayTeam() {
        System.out.println("This is your team!\n");

        if(!this.team.isEmpty()) {
            for (Pokemon p : this.team) {
                p.displayPokemon();
            }
        } else {
            System.out.println("You don't have any pokemon on your team. Try searching and adding them to you team first!");
        }
    }

    public void addPokemon(Pokemon p) {
        if (this.team.size() < 6) {
            int index = this.team.size();

            this.team.add(index, p);

            File file = new File("team.txt");
            File filetemp = new File("temp.txt");
            try {
                String userTeam = "";
                Scanner sc = new Scanner(file);
                FileWriter temp = new FileWriter(filetemp, true);
                Scanner tempsc = new Scanner(filetemp);

                while(sc.hasNextLine()) {
                    String line = sc.nextLine().strip();
                    String[] user = line.split(",");
                    
                    if(user[0].strip().equals(String.valueOf(this.id))) {
                        userTeam = line;
                        continue;
                    }

                    temp.append(line + "\n");
                    temp.flush();
                }

                file.delete();
                FileWriter fw = new FileWriter("team.txt", true);

                while(tempsc.hasNextLine()) {
                    String line = tempsc.nextLine().strip();

                    fw.append(line + "\n");
                    fw.flush();
                }

                String joinedString = String.join(", ", userTeam);
                fw.append(joinedString + ", " + p.getId());

                fw.flush();
                fw.close();

                filetemp.delete();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println("\nSorry you can only have 6 pokemon in your team\n");
        }

    }

    public void removePokemon(String id) {
        int index = -1;

        for (int i  = 0; i < this.team.size(); i++) {
            if(this.team.get(i).getId().equals(id)) {
                index = i;
            }
        }

        if (index != -1) {
            this.team.remove(index);

            File file = new File("team.txt");
            File filetemp = new File("temp.txt");
            try {
                String userTeam = "";
                Scanner sc = new Scanner(file);
                FileWriter temp = new FileWriter(filetemp, true);
                Scanner tempsc = new Scanner(filetemp);

                while(sc.hasNextLine()) {
                    String line = sc.nextLine().strip();
                    String[] user = line.split(",");
                    
                    if(user[0].strip().equals(String.valueOf(this.id))) {
                        userTeam = line;
                        continue;
                    }

                    temp.append(line + "\n");
                    temp.flush();
                }

                file.delete();
                FileWriter fw = new FileWriter("team.txt", true);

                while(tempsc.hasNextLine()) {
                    String line = tempsc.nextLine().strip();

                    fw.append(line + "\n");
                    fw.flush();
                }

                String[] teamArray = userTeam.split(",");
                String[] newTeam = new String[teamArray.length - 1];

                for(int i = 0, j = 0; i < teamArray.length; i++) {
                    if(!teamArray[i].strip().equals(id)) {
                        newTeam[j++] = teamArray[i];
                    }
                }

                fw.append(String.join(",", newTeam));

                fw.flush();
                fw.close();

                filetemp.delete();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("\nLooks like that pokemon isn't in your team. Please make sure you entered your id correctly\n");
        }
    }

    private ArrayList<Pokemon> fillTeam() {
        ArrayList<Pokemon> pokeTeam = new ArrayList<Pokemon>();
        File file = new File("team.txt");

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] entry = line.strip().split(",");
                if(entry[0].equals(Integer.toString(this.id))) {
                    if (entry.length > 1) {
                        for(int i = 1; i < entry.length; i++) {
                            String pokeid = entry[i].strip();

                            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokeid);

                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            
                            con.setRequestMethod("GET");


                            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String inputLine;
                            StringBuffer content = new StringBuffer();

                            while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                            }

                            JSONObject obj = new JSONObject(content.toString());
                            org.json.JSONArray stats = obj.getJSONArray("stats");
                            org.json.JSONArray JSONTypes = obj.getJSONArray("types");
                            String[] types = new String[JSONTypes.length()];
                            
                            String name = obj.getString("name");

                            int hp = stats.getJSONObject(0).getInt("base_stat");
                            int attack = stats.getJSONObject(1).getInt("base_stat");
                            int defence = stats.getJSONObject(2).getInt("base_stat");
                            int spAttack = stats.getJSONObject(3).getInt("base_stat");
                            int spDefence = stats.getJSONObject(4).getInt("base_stat");
                            int speed = stats.getJSONObject(5).getInt("base_stat");

                            for (int j = 0; j < JSONTypes.length(); j++) {
                                types[j] = JSONTypes.getJSONObject(j).getJSONObject("type").getString("name");
                            }

                            HashMap<String, Integer> pokeStats = new HashMap<String, Integer>();

                            pokeStats.put("HP", hp);
                            pokeStats.put("Attack", attack);
                            pokeStats.put("Defence", defence);
                            pokeStats.put("Sp. Attack", spAttack);
                            pokeStats.put("Sp. Defence", spDefence);
                            pokeStats.put("Speed", speed);

                            Pokemon p = new Pokemon(pokeid, name, types, pokeStats);

                            pokeTeam.add(p);
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Something happened *shrug*");
        }

        return pokeTeam;
    }

}
