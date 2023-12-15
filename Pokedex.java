import java.net.*;
import java.io.*;
import java.util.*;

public class Pokedex {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("This program uses data taken from https://pokemondb.net/");

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

                // Check type(s) and generation
                position = 0;
                position = line.indexOf("<em>"+ String.valueOf(pokemonName.charAt(0)).toUpperCase() + pokemonName.substring(1), position + 1);
                if (position > -1) {
                    String info = line.substring(position, line.indexOf("</abbr>", position + 1)); // Get all information in first sentence
                    String type = info.substring(info.indexOf("/type/") + 6, info.indexOf("\" class=\"itype"));
                    if (info.indexOf("/type/") != (info.lastIndexOf("/type/"))) { // Check if there is more than one type
                        type = type + " + " + info.substring(info.lastIndexOf("/type/") + 6, info.lastIndexOf("\" class=\"itype"));
                    }
                    //System.out.println(info);
                    int generation = Integer.parseInt(info.substring(info.indexOf("Generation") + 11));
                    System.out.println("Type: " + type.toUpperCase());
                    System.out.println("Introduced: " + "GENERATION " + generation);
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
