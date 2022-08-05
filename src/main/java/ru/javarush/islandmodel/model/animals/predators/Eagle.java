package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;

@Characteristics(weight = 6, maxSatiety = 1, maxOnOneLocation = 20, possibleDistance = 3)
public class Eagle extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Fox.class, 10, Rabbit.class, 90, Mouse.class, 90, Duck.class, 80);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Eagle";
    }
}