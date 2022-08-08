package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;

import java.util.Map;

@Characteristics(weight = 0.05, maxSatiety = 0.01, maxOnOneLocation = 500, possibleDistance = 1)
public class Mouse extends Herbivore {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Caterpillar.class, 90);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Mouse";
    }


}