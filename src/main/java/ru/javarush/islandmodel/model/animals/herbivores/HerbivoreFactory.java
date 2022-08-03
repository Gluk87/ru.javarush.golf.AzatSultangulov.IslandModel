package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.AnimalFactory;
import ru.javarush.islandmodel.model.animals.AnimalTypes;

public class HerbivoreFactory implements AnimalFactory {
    @Override
    public Herbivore createAnimal (AnimalTypes type) {
        Herbivore herbivore;
        switch (type) {
            case BOAR -> herbivore = new Boar();
            case BUFFALO -> herbivore = new Buffalo();
            case CATERPILLAR -> herbivore = new Caterpillar();
            case DEER -> herbivore = new Deer();
            case DUCK -> herbivore = new Duck();
            case GOAT -> herbivore = new Goat();
            case HORSE -> herbivore = new Horse();
            case MOUSE -> herbivore = new Mouse();
            case RABBIT -> herbivore = new Rabbit();
            case SHEEP -> herbivore = new Sheep();
            default -> herbivore = null;
        }
        return herbivore;
    }
}

