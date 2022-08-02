package ru.javarush.model.animals;

public interface AnimalFactory {
    Animal createAnimal(AnimalTypes types);
}
