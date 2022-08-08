package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.model.island.Location;

public class LifeCycle implements Runnable{
    Location location;
    Island island;

    public LifeCycle(Location location, Island island) {
        this.location = location;
        this.island = island;
    }

    @Override
    public void run() {
        location.eating();
        location.breeding();
        location.moving(island.getLocations());
        location.dying();
    }
}
