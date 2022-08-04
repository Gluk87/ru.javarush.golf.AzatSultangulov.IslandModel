package ru.javarush.islandmodel.model.animals.herbivores;


import ru.javarush.islandmodel.model.animals.Characteristics;

@Characteristics(weight = 0.05, maxSatiety = 0.01, maxOnOneLocation = 500, possibleDistance = 1)
public class Mouse extends Herbivore {
    @Override
    public String toString() {
        return "Mouse";
    }
}