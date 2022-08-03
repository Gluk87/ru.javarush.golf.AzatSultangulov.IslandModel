package ru.javarush.islandmodel.exceptions;

public class MissingAnnotationException extends RuntimeException {
    public MissingAnnotationException(String message) {
        super(message);
    }
}