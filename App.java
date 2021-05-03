import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static String returningTrainer() {
        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.print("Are you a returning trainer?(y/n): ");
        choice = sc.next();

        while (!choice.equals("y") && !choice.equals("n")) {
            System.out.println("\nSorry that was not a valid choice\n");

            System.out.print("Are you a returning trainer?(y/n): ");
            choice = sc.next();
        }
        
        return choice;
    }

    public static Trainer loginTrainer() {
        String username;
        String password;
        String[] user = null;
        Trainer t = null;
        Scanner sc = new Scanner(System.in);

        try {
            File file = new File("trainers.txt");
            Scanner kb = new Scanner(file);

            System.out.print("\nPlease enter your username: ");
            username = sc.next().strip();

            while(kb.hasNextLine()) {
                String line = kb.nextLine();
                String[] entry = line.strip().split(",");

                if(entry[1].strip().equals(username)) {
                    user = entry;
                    break;
                }
            }

            if (user == null) {
                System.out.println("\nSorry looks like you weren't in our system please\n");
                loginTrainer();
            } else {
                System.out.print("please enter your password: ");
                password = sc.next().strip();

                while(!user[2].strip().equals(password)) {
                    System.out.println("\nWrong password please try again\n");

                    System.out.print("please enter your password: ");
                    password = sc.next().strip();
                }

                if (user[2].strip().equals(password)) {
                    System.out.println("\nLog in succesful\n");
                    t = new Trainer(Integer.parseInt(user[0].strip()), username, password);
                }
            }


        } catch (Exception e) {
        }

        return t;
    }

    public static Trainer createTrainer() {
        String username;
        String password;
        ArrayList<String> users = new ArrayList<String>();
        int count = 0;
        Trainer t = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nLet's create a new trainer!");

        try {
            File file = new File("trainers.txt");
            Scanner kb = new Scanner(file);
            FileWriter fw = new FileWriter(file, true);


            while(kb.hasNextLine()) {
                String line = kb.nextLine();
                String[] entry = line.strip().split(",");
                users.add(entry[1].strip());
                count++;
            }

            System.out.print("\nPlease enter your username: ");
            username = sc.next().strip();

            while (users.contains(username.strip())) {
                System.out.println("Sorry looks like the username is already taken please try entering a new one.");
                username = sc.next().strip();
            }


            System.out.print("\nPlease enter your password: ");
            password = sc.next().strip();

            fw.append((count++ + ", " + username + ", " + password + "\n"));
            
            fw.flush();
            fw.close();

            t = new Trainer(count+1, username, password);

        } catch (Exception e) {
            System.out.println("can't find file");
        }

        return t;
    }

    public static void displayMenu() {
        System.out.println("\n\nMenu\n");
        System.out.println("1. search pokemon");
        System.out.println("2. add pokemon");
        System.out.println("3. remove pokemon");
        System.out.println("4. display team");
        System.out.println("5. quit");
    }

    public static void searchPokemon(int pokeid, Trainer t) {
        URL url;
        Scanner sc;
        try {
            sc = new Scanner(System.in);

            url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokeid);

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

            Pokemon p = new Pokemon(String.valueOf(pokeid), name, types, pokeStats);

            System.out.println("");
            p.displayPokemon();

            System.out.println("\nWould you like to add this pokemon to your team:(y/n)");
            String choice = sc.next();

            while(!choice.equals("y") && !choice.equals("n")) {
                System.out.println("Sorry I didn't get that please try again");

                System.out.println("\nWould you like to add this pokemon to your team:(y/n)");
                choice = sc.next();
            }

            if(choice.equals("y")) {
                t.addPokemon(p);
            }

        } catch (Exception e) {
            System.out.println("error");
        }
    }



    public static void main(String[] args) {
        Trainer t;
        String returning;
        int choice;
        Scanner sc = new Scanner(System.in);

        returning = returningTrainer();

        if (returning.equals("y")) {
            t = loginTrainer();
            System.out.println("Welcome trainer " + t.getUsername() + "!");
        } else {
            t = createTrainer();
            System.out.println("Welcome trainer " + t.getUsername() + "!");
        }

        t.displayTeam();
        displayMenu();
        choice = sc.nextInt();

        while(choice != 5) {
            if(choice == 1) {
                System.out.print("Please enter the pokemon you would like to search for: ");
                int pokeid = sc.nextInt();

                searchPokemon(pokeid, t);
            }
            
            if(choice == 2) {
                
            }
            
            if(choice ==3) {
                
            }
            
            if(choice == 4) {
                t.displayTeam();
            }

            displayMenu();
            choice = sc.nextInt();
        }
        

    }
}
