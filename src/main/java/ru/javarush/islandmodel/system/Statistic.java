package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.model.animals.AnimalTypes;
import ru.javarush.islandmodel.model.animals.herbivores.Herbivore;
import ru.javarush.islandmodel.model.animals.predators.Predator;
import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.model.island.Location;

import java.util.ArrayList;
import java.util.List;

public class Statistic implements Runnable{
    private final Island island;
    private final Location[][] locations;

    public Statistic(Island island) {
        this.island = island;
        this.locations = island.getLocations();
    }

    @Override
    public void run() {
        printInfo();
    }

    public void printInfo() {
        System.out.println("==================================================");
        System.out.print("Count of predators: " + island.getCountPredators());
        System.out.print(", Count of herbivores: " + island.getCountHerbivores());
        System.out.println(", Total: " + island.getCountAnimals());
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Location location = locations[i][j];
                System.out.println("Location [" + location.getCoordinates().getX() + ", " + location.getCoordinates().getY() + "]");
                System.out.print("Predators [ ");
                for (AnimalTypes animalType: AnimalTypes.values()) {
                    List<Predator> predators = new ArrayList<>(location.getPredators()) ;
                    int countPredators = (int) predators.stream().filter(p -> p.toString().equalsIgnoreCase(animalType.toString())).count();
                    if (countPredators > 0) {
                        System.out.print(animalType + ":" + countPredators + " ");
                    }
                }
                System.out.println(" ]");
                System.out.print("Herbivores [ ");
                for (AnimalTypes animalType: AnimalTypes.values()) {
                    List<Herbivore> herbivores = new ArrayList<>(location.getHerbivores()) ;
                    int countHerbivores = (int) herbivores.stream().filter(p -> p.toString().equalsIgnoreCase(animalType.toString())).count();
                    if (countHerbivores > 0) {
                        System.out.print(animalType + ":" + countHerbivores + " ");
                    }
                }
                System.out.println(" ]");
                System.out.println("Plants [ " + location.getPlants().size() + " ]");
            }
        }
    }
}
