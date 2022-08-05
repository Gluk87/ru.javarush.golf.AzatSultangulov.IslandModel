package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;

@Characteristics(weight = 500, maxSatiety = 80, maxOnOneLocation = 5, possibleDistance = 2)
public class Bear extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Boa.class, 80, Horse.class, 40, Deer.class, 80, Rabbit.class, 80,
                    Mouse.class, 90, Goat.class, 70, Sheep.class, 70, Boar.class, 50,
                    Buffalo.class, 20, Duck.class, 70);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Bear";
    }
}