package ru.javarush.islandmodel.model.animals.predators;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.islandmodel.model.animals.Animal;
import ru.javarush.islandmodel.model.animals.Characteristics;
import ru.javarush.islandmodel.model.animals.Gender;
import ru.javarush.islandmodel.model.animals.herbivores.*;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 500, maxSatiety = 80, maxOnOneLocation = 5, possibleDistance = 2)
public class Bear extends Predator {
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    public Bear() {
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
        return "Bear";
    }
}

