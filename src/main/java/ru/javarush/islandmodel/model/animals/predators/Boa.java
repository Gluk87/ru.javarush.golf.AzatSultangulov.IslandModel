package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;

@Characteristics(weight = 15, maxSatiety = 3, maxOnOneLocation = 30, possibleDistance = 1)
public class Boa extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Fox.class, 15, Rabbit.class, 20, Mouse.class, 40, Duck.class, 10);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Boa";
    }
}