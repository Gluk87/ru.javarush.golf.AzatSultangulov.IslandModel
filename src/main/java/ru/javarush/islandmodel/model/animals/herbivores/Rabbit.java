package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 2, maxSatiety = 0.45, maxOnOneLocation = 150, possibleDistance = 2)
public class Rabbit extends Herbivore{
    @Override
    public String toString() {
        return "Rabbit";
    }
}

