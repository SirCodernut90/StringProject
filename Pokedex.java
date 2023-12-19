// Import statements
import java.net.*;
import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Pokedex extends JFrame implements ActionListener {
    private JTextField input;
    private JTextArea display;

    // GUI Constructor
    public Pokedex()  {
        super("Pokédex");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Pokémon name:"));
        input = new JTextField(5);
        input.setBackground(Color.YELLOW);
        input.addActionListener(this);
        c.add(input);

        display = new JTextArea(10, 50);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        c.add(new JScrollPane(display, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));

    }

    public void actionPerformed(ActionEvent e)  {
        String pokemonName = "";
        try {
            pokemonName = input.getText();
            if (pokemonName.equalsIgnoreCase("quit") || pokemonName.equals(" ")) { // Quit program
                return;
            }
            pokemonName = pokemonName.trim().toLowerCase();
            pokemonName = String.valueOf(pokemonName.charAt(0)).toUpperCase() + pokemonName.substring(1); // Capitalize beginning of each word
            if (pokemonName.contains(" ")) { // Has multiple spaces, capitalize
                int firstIndex = pokemonName.indexOf(" ");
                pokemonName = pokemonName.substring(0, firstIndex) + pokemonName.substring(firstIndex, firstIndex + 2).toUpperCase() + pokemonName.substring(firstIndex + 2);
            }

            String webpageURL = "";
            if (pokemonName.contains(" ")) { // Check if there is a space in the name, replace with "-" for URL
                webpageURL = "https://pokemondb.net/pokedex/" + pokemonName.replace(" ", "-").toLowerCase();
            } else {
                webpageURL = "https://pokemondb.net/pokedex/" + pokemonName.toLowerCase();
            }

            URL webpage = new URI(webpageURL).toURL(); // Creates a URL object
            URLConnection connection = webpage.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine, line = "";
            while ((inputLine = reader.readLine()) != null) {
                line += inputLine;
            }
            reader.close();

            pokemon.setName(pokemonName);

            // Check base stats
            int position = 0;
            position = line.indexOf("cell-num cell-total", position + 1);
            if (position > -1) {
                int totalStats = Integer.parseInt(line.substring(position + 21, line.indexOf("</td>", position + 1)));
                pokemon.setTotalStats(totalStats);
            }

            // Check type(s) and generation
            position = 0;
            position = line.indexOf("<em>", position + 1);
            if (position > -1) {
                String info = line.substring(position, line.indexOf("</abbr>", position + 1)); // Get all information in first sentence
                String type = info.substring(info.indexOf("/type/") + 6, info.indexOf("\" class=\"itype"));
                if (info.indexOf("/type/") != (info.lastIndexOf("/type/"))) { // Check if there is more than one type
                    type = type + " + " + info.substring(info.lastIndexOf("/type/") + 6, info.lastIndexOf("\" class=\"itype"));
                }
                //System.out.println(info);
                int generation = Integer.parseInt(info.substring(info.indexOf("Generation") + 11));
                pokemon.setType(type);
                pokemon.setGen(generation);
            }
            System.out.println(pokemon);

        } catch (MalformedURLException exception) {
            System.out.println("Malformed URL Exception found");
        } catch (IOException exception) {
            System.out.println("Pokémon not found");
        } catch (URISyntaxException exception) {
            System.out.println("Invalid name");
        }
    }


    public static Pokemon pokemon = new Pokemon();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create window
        Pokedex window = new Pokedex();
        window.setBounds(100, 100, 500, 500);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

        System.out.println("This program uses data taken from https://pokemondb.net/");


//        boolean loop = true;
//        while (loop) {
//            try {
//                System.out.print("\nEnter Pokémon name or \"quit\": "); // Get user input
//                pokemonName = input.nextLine();
//                if (pokemonName.equalsIgnoreCase("quit") || pokemonName.equals(" ")) { // Quit program
//                    loop = false;
//                    break;
//                }
//                pokemonName = pokemonName.trim().toLowerCase();
//                pokemonName = String.valueOf(pokemonName.charAt(0)).toUpperCase() + pokemonName.substring(1); // Capitalize beginning of each word
//                if (pokemonName.contains(" ")) { // Has multiple spaces, capitalize
//                    int firstIndex = pokemonName.indexOf(" ");
//                    pokemonName = pokemonName.substring(0, firstIndex) + pokemonName.substring(firstIndex, firstIndex + 2).toUpperCase() + pokemonName.substring(firstIndex + 2);
//                }
//
//                String webpageURL = "";
//                if (pokemonName.contains(" ")) { // Check if there is a space in the name, replace with "-" for URL
//                    webpageURL = "https://pokemondb.net/pokedex/" + pokemonName.replace(" ", "-").toLowerCase();
//                } else {
//                    webpageURL = "https://pokemondb.net/pokedex/" + pokemonName.toLowerCase();
//                }
//
//                URL webpage = new URI(webpageURL).toURL(); // Creates a URL object
//                URLConnection connection = webpage.openConnection();
//        		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine, line = "";
//                while ((inputLine = reader.readLine()) != null) {
//                    line += inputLine;
//                }
//                reader.close();
//
//                pokemon.setName(pokemonName);
//
//                // Check base stats
//                int position = 0;
//                position = line.indexOf("cell-num cell-total", position + 1);
//                if (position > -1) {
//                    int totalStats = Integer.parseInt(line.substring(position + 21, line.indexOf("</td>", position + 1)));
//                    pokemon.setTotalStats(totalStats);
//                }
//
//                // Check type(s) and generation
//                position = 0;
//                position = line.indexOf("<em>", position + 1);
//                if (position > -1) {
//                    String info = line.substring(position, line.indexOf("</abbr>", position + 1)); // Get all information in first sentence
//                    String type = info.substring(info.indexOf("/type/") + 6, info.indexOf("\" class=\"itype"));
//                    if (info.indexOf("/type/") != (info.lastIndexOf("/type/"))) { // Check if there is more than one type
//                        type = type + " + " + info.substring(info.lastIndexOf("/type/") + 6, info.lastIndexOf("\" class=\"itype"));
//                    }
//                    //System.out.println(info);
//                    int generation = Integer.parseInt(info.substring(info.indexOf("Generation") + 11));
//                    pokemon.setType(type);
//                    pokemon.setGen(generation);
//                }
//                System.out.println(pokemon);
//
//            } catch (MalformedURLException e) {
//                System.out.println("Malformed URL Exception found");
//            } catch (IOException e) {
//                System.out.println("Pokémon not found");
//            } catch (URISyntaxException e) {
//                System.out.println("Invalid name");
//            }
//       }

    }
}

