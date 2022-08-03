package ru.javarush.islandmodel.model.animals.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.Gender;
import ru.javarush.islandmodel.model.island.Location;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 0.01, maxSatiety = 0, maxOnOneLocation = 1000)
public class Caterpillar extends Herbivore {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Caterpillar() {
        this.gender = getRandomGender();
        this.satiety = 0;
        this.isBreed = false;
    }

    @Override
    public boolean move(Location currentLocation, Location[][] locations) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(satiety);
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }
}

