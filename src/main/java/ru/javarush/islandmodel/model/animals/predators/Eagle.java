package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 6, maxSatiety = 1, maxOnOneLocation = 20, possibleDistance = 3)
public class Eagle extends Predator {

    @Override
    public String toString() {
        return "Eagle";
    }
}