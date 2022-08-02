package ru.javarush.model.animals.predators;

import ru.javarush.model.animals.Animal;
import ru.javarush.model.animals.Gender;
import ru.javarush.model.island.Location;

import java.util.List;

public abstract class Predator extends Animal {

    @Override
    public void breed(Location location) {
        List<Predator> predatorsFemale = location.getPredators().stream().filter(predator ->
                predator.getGender() == Gender.FEMALE && !predator.isBreed()).toList();
        if (predatorsFemale.isEmpty()) {
            return;
        }
        for (int i = 0; i < predatorsFemale.size(); i++) {
            Predator predatorFemale = predatorsFemale.get(i);
            if (this.getGender() == Gender.MALE && !this.isBreed() && this.equals(predatorFemale)) {
                this.setBreed(true);
                this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                predatorFemale.setBreed(true);
                predatorFemale.setSatiety(Math.max(0, predatorFemale.getSatiety() - predatorFemale.getMaxSatiety() / 10));
                location.getPredators().add(this);
            }
        }
    }
}
