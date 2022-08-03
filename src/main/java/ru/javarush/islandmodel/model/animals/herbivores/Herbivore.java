package ru.javarush.islandmodel.model.animals.herbivores;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Gender;
import ru.javarush.islandmodel.model.island.Location;

import java.util.List;

public abstract class Herbivore extends Animal {

    @Override
    public void breed(Location location) {
        List<Herbivore> herbivoresFemale = location.getHerbivores().stream().filter(herbivore ->
                herbivore.getGender() == Gender.FEMALE && !herbivore.isBreed()).toList();
        if (herbivoresFemale.isEmpty()) {
            return;
        }
        for (int i = 0; i < herbivoresFemale.size(); i++) {
            Herbivore herbivoreFemale = herbivoresFemale.get(i);
            if (this.getGender() == Gender.MALE && !this.isBreed() && this.equals(herbivoreFemale)) {
                this.setBreed(true);
                this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                herbivoreFemale.setBreed(true);
                herbivoreFemale.setSatiety(Math.max(0, herbivoreFemale.getSatiety() - herbivoreFemale.getMaxSatiety() / 10));
                location.getHerbivores().add(this);
            }
        }
    }
}

