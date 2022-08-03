package ru.javarush.islandmodel.model.animals;

public interface AnimalFactory {
    Animal createAnimal(AnimalTypes types);
}
