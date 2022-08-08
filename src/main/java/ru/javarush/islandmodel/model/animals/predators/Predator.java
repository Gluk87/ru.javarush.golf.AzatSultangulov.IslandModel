package ru.javarush.islandmodel.model.animals.predators;

import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Gender;
import ru.javarush.islandmodel.model.island.Location;

import java.util.List;

public abstract class Predator extends Animal {

    @Override
    public boolean breed(Location location) {
        location.getLock().lock();
        try {
            List<Predator> predatorsFemale = location.getPredators().stream().filter(predator ->
                    predator.getGender() == Gender.FEMALE && !predator.isBreed()).toList();
            if (predatorsFemale.isEmpty()) {
                return false;
            }
            for (int i = 0; i < predatorsFemale.size(); i++) {
                Predator predatorFemale = predatorsFemale.get(i);
                if (this.getGender() == Gender.MALE && !this.isBreed() && this.equals(predatorFemale)) {
                    this.setBreed(true);
                    this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                    predatorFemale.setBreed(true);
                    predatorFemale.setSatiety(Math.max(0, predatorFemale.getSatiety() - predatorFemale.getMaxSatiety() / 10));
                    return true;
                }
            }
            return false;
        } finally {
            location.getLock().unlock();
        }
    }
}
