package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 15, maxSatiety = 3, maxOnOneLocation = 30, possibleDistance = 1)
public class Boa extends Predator {

    @Override
    public String toString() {
        return "Boa";
    }
}