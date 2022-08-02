package ru.javarush.model.animals.herbivores;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.model.animals.Characteristics;
import ru.javarush.model.animals.Gender;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 1, maxSatiety = 0.015, maxOnOneLocation = 200, possibleDistance = 4)
public class Duck extends Herbivore {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Duck() {
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
        return "Duck";
    }
}

