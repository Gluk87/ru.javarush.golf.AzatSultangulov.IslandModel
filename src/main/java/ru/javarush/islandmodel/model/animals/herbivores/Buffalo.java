package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 700, maxSatiety = 100, maxOnOneLocation = 10, possibleDistance = 3)
public class Buffalo extends Herbivore {

    @Override
    public String toString() {
        return "Buffalo";
    }
}