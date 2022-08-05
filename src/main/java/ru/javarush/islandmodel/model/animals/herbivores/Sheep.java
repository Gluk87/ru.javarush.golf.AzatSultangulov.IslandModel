package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 70, maxSatiety = 15, maxOnOneLocation = 140, possibleDistance = 3)
public class Sheep extends Herbivore {

    @Override
    public String toString() {
        return "Sheep";
    }
}