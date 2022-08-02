package ru.javarush.model.island;

import lombok.Getter;

@Getter
public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
