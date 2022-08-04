package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.model.island.Location;
import ru.javarush.islandmodel.model.plants.Plant;

public class PlantGrowth implements Runnable{
    private final Island island;
    private final Location[][] locations;

    public PlantGrowth(Island island) {
        this.island = island;
        this.locations = island.getLocations();
    }

    @Override
    public void run() {
        growth();
    }

    public void growth() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                int currentCountPlants = locations[i][j].getPlants().size();
                int maxCount = locations[i][j].getMaxOnOneLocation(Plant.class);
                int plantRandomCount = locations[i][j].getRandomCount(maxCount);
                if (currentCountPlants + plantRandomCount > maxCount) {
                    plantRandomCount = maxCount - currentCountPlants;
                }
                for (int k = 0; k < plantRandomCount; k++) {
                    locations[i][j].getPlants().add(new Plant());
                }

            }
        }
    }
}
