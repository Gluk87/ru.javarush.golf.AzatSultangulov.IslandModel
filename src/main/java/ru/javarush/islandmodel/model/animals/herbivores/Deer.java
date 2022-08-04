package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 300, maxSatiety = 50, maxOnOneLocation = 20, possibleDistance = 4)
public class Deer extends Herbivore{

    @Override
    public String toString() {
        return "Deer";
    }
}
