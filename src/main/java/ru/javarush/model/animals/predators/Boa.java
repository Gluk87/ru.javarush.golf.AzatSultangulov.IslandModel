package ru.javarush.model.animals.predators;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.model.animals.Characteristics;
import ru.javarush.model.animals.Gender;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 15, maxSatiety = 3, maxOnOneLocation = 30, possibleDistance = 1)
public class Boa extends Predator {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Boa() {
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
        return "Boa";
    }
}
