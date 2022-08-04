package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 1, maxSatiety = 0.015, maxOnOneLocation = 200, possibleDistance = 4)
public class Duck extends Herbivore {

    @Override
    public String toString() {
        return "Duck";
    }
}