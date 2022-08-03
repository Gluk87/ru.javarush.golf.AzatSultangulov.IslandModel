package ru.javarush.islandmodel.model.animals.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.Gender;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 70, maxSatiety = 15, maxOnOneLocation = 140, possibleDistance = 3)
public class Sheep extends Herbivore {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Sheep() {
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
        return "Sheep";
    }
}

