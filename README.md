# StringProject
Zach and Sanjay
AP Java 2023-24
Block A

This is the a simple pokedex model made from website scraping. We used [https://pokemondb.net/pokedex/all](url), a page in which all pokemon names have been listed. Then, depending on the user input, we modified the URL (it followed a pattern) so that it found the page with the data about the specific pokemon. We then took the information we required and organized it as we needed.

Our program follows a simple 2 class structure. One class, Pokemon, carries the information about the pokemon in String format, and has setters for the field, while the other, Pokedex, serves as the client window and website scraper. It takes the URL and organizes it into the GUI so that all information is displayed as desired, with a picture of the pokemon on the right. 
