package ru.javarush.model.animals.predators;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.model.animals.Characteristics;
import ru.javarush.model.animals.Gender;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 50, maxSatiety = 8, maxOnOneLocation = 30, possibleDistance = 3)
public class Wolf extends Predator {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Wolf() {
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
        return "Wolf";
    }
}

