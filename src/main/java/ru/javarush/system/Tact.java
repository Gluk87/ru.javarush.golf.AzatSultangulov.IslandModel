package ru.javarush.system;

import ru.javarush.model.island.Island;
import ru.javarush.model.island.Location;

public class Tact implements Runnable{
    private Island island;
    private final Location[][] locations;

    public Tact(Island island) {
        this.island = island;
        this.locations = island.getLocations();
    }

    @Override
    public void run() {
        System.out.println("Count of animals: " + island.getCountAnimals());
        for (int i = 0; i < island.getLength(); i++) {
            for (int j = 0; j < island.getWidth(); j++) {
                locations[i][j].eating();
                System.out.println("поели");
                locations[i][j].breeding();
                System.out.println("размножились");
                locations[i][j].moving();
                System.out.println("подвигались");
                locations[i][j].dying();
                System.out.println("умерли");
            }
        }
    }
}

