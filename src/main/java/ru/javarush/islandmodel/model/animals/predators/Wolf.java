package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;

@Characteristics(weight = 50, maxSatiety = 8, maxOnOneLocation = 30, possibleDistance = 3)
public class Wolf extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Deer.class, 15, Rabbit.class, 60, Mouse.class, 80, Goat.class, 60,
                    Sheep.class, 70, Boar.class, 15, Buffalo.class, 10, Duck.class, 40);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Wolf";
    }
}