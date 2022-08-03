package ru.javarush.islandmodel.model.plants;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.islandmodel.exceptions.MissingAnnotationException;
import ru.javarush.islandmodel.model.animals.Characteristics;

import java.util.Objects;

@Getter
@Setter
@Characteristics(weight = 50, maxSatiety = 0, maxOnOneLocation = 200)
public class Plant {

    public Plant create() {
        return new Plant();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        return "Plant";
    }

    public double getWeight() {
        return this.getCharacteristics().weight();
    }

    private Characteristics getCharacteristics() {
        if (!this.getClass().isAnnotationPresent(Characteristics.class)){
            throw new MissingAnnotationException("Missing annotation " + Characteristics.class +" for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(Characteristics.class);
    }
}

