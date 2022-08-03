package ru.javarush.islandmodel.model.animals.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.Gender;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 400, maxSatiety = 60, maxOnOneLocation = 20, possibleDistance = 4)
public class Horse extends Herbivore {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Horse() {
        this.gender = getRandomGender();
        this.satiety = this.getMaxSatiety()/2;
        this.isBreed = false;
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
        return "Horse";
    }
}