package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.AnimalFactory;
import ru.javarush.islandmodel.model.animals.AnimalTypes;

public class PredatorFactory implements AnimalFactory {
    @Override
    public Predator createAnimal (AnimalTypes type) {
        Predator predator;
        switch (type) {
            case BEAR -> predator = new Bear();
            case BOA -> predator = new Boa();
            case EAGLE -> predator = new Eagle();
            case FOX -> predator = new Fox();
            case WOLF -> predator = new Wolf();
            default -> predator = null;
        }
        return predator;
    }
}
