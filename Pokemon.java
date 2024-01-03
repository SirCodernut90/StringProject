import java.net.*;
import java.io.*;
import java.util.*;

public class Pokemon {
    private String name;
    private int generation;
    private String type;
    private String evolutions;
    private int totalStats;

    public Pokemon() {

    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGen(int generation) {
        this.generation = generation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalStats(int totalStats) {
        this.totalStats = totalStats;
    }

    public void setEvolutions(String evolutions) {
        this.evolutions = evolutions;
    }

    // Getters
    public int getTotalStats() {
        return totalStats;
    }

    public String toString() {
        return name + "\nType: " + type.toUpperCase() + "\nIntroduced: Generation " + generation + "\nTotal Stats: " + totalStats;
    }
}
