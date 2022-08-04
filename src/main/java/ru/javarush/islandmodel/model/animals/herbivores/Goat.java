package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 60, maxSatiety = 10, maxOnOneLocation = 140, possibleDistance = 3)
public class Goat extends Herbivore {
    @Override
    public String toString() {
        return "Goat";
    }
}
