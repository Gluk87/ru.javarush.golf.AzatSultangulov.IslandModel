package ru.javarush.islandmodel.model.animals;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Characteristics {
    double weight();
    double maxSatiety();
    int maxOnOneLocation();
    int possibleDistance() default 0;
}
