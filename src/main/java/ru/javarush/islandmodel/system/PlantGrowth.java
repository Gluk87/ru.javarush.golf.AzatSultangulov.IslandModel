package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.model.island.Location;
import ru.javarush.islandmodel.model.plants.Plant;

public class PlantGrowth implements Runnable{
    private final Location location;

    public PlantGrowth(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        growth();
    }

    public void growth() {
        int currentCountPlants = location.getPlants().size();
        int maxCount = location.getMaxOnOneLocation(Plant.class);
        int plantRandomCount = location.getRandomCount(maxCount);
        if (currentCountPlants + plantRandomCount > maxCount) {
            plantRandomCount = maxCount - currentCountPlants;
        }
        for (int k = 0; k < plantRandomCount; k++) {
            location.getPlants().add(new Plant());
        }
    }
}
