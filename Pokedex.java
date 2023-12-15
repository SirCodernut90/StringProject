import java.net.*;
import java.io.*;
import java.util.*;

public class Pokedex {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pokemon pokemon = new Pokemon();
        String pokemonName = "";

        boolean loop = true;
        while (loop) {
            try {
                System.out.print("Enter Pokémon name or \"quit\": "); // Get user input
                pokemonName = input.nextLine();

                if (pokemonName.equalsIgnoreCase("quit")) { // Quit program
                    loop = false;
                    break;
                }

                pokemon.setName(pokemonName);

                pokemonName = pokemonName.trim().toLowerCase();
                String webpageURL = "https://pokemondb.net/pokedex/" + pokemonName;
                URL webpage = new URI(webpageURL).toURL(); // Creates a URL object
                URLConnection connection = webpage.openConnection();
        		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine, line = "";
                while ((inputLine = reader.readLine()) != null) {
                    line += inputLine;
                }

                reader.close();

                // Check base stats
                int position = 0;
                position = line.indexOf("cell-num cell-total", position + 1);
                if (position > -1) {
                    int totalStats = Integer.parseInt(line.substring(position + 21, line.indexOf("</td>", position + 1)));
                    System.out.println("Total Base Stats: " + totalStats);
                }

                // Check type(s)
                position = 0;
                position = line.indexOf("itype", position + 1);
                if (position > -1) {
                    String type = line.substring(position + 6, line.indexOf("\">", position + 1));
                   System.out.println("Type: " + type.toUpperCase());
                }

            } catch (MalformedURLException e) {
                System.out.println("Malformed URL Exception found");
            } catch (IOException e) {
                System.out.println("Pokémon not found");
            } catch (URISyntaxException e) {
                System.out.println("Invalid name");
            }


        }
    }
}
