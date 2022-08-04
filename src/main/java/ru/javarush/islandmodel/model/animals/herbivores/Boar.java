package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 400, maxSatiety = 50, maxOnOneLocation = 50, possibleDistance = 2)
public class Boar extends Herbivore {

    @Override
    public String toString() {
        return "Boar";
    }
}