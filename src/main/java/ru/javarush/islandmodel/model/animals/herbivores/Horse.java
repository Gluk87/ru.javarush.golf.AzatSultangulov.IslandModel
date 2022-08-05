package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 400, maxSatiety = 60, maxOnOneLocation = 20, possibleDistance = 4)
public class Horse extends Herbivore {

    @Override
    public String toString() {
        return "Horse";
    }
}