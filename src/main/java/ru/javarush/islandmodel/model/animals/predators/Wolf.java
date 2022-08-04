package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 50, maxSatiety = 8, maxOnOneLocation = 30, possibleDistance = 3)
public class Wolf extends Predator {

    @Override
    public String toString() {
        return "Wolf";
    }
}