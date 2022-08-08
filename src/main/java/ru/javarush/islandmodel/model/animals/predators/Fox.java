package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;

@Characteristics(weight = 8, maxSatiety = 2, maxOnOneLocation = 30, possibleDistance = 2)
public class Fox extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Caterpillar.class, 40);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Fox";
    }
}