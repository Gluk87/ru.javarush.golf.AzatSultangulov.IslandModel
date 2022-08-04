package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 8, maxSatiety = 2, maxOnOneLocation = 30, possibleDistance = 2)
public class Fox extends Predator {

    @Override
    public String toString() {
        return "Fox";
    }
}