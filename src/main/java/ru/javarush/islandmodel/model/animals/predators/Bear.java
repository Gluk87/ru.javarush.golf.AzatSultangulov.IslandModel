package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 500, maxSatiety = 80, maxOnOneLocation = 5, possibleDistance = 2)
public class Bear extends Predator {

    @Override
    public String toString() {
        return "Bear";
    }
}