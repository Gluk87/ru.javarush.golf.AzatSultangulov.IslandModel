package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.island.Location;

@Characteristics(weight = 0.01, maxSatiety = 0, maxOnOneLocation = 1000)
public class Caterpillar extends Herbivore {

    @Override
    public boolean move(Location currentLocation, Location[][] locations) {
        return false;
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }
}

