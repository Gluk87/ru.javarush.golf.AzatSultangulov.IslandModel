package ru.javarush.islandmodel.model.island;

import lombok.Getter;

@Getter
public class Island {
    private final int length;
    private final int width;
    private final Location[][] locations;

    public Island(int length, int width) {
        this.length = length;
        this.width = width;
        this.locations = new Location[length][width];
    }

    public void initialize() {
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++) {
                locations[i][j] = new Location(new Coordinates(i, j));
            }
        }
    }

    public int getCountPredators() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                count += locations[i][j].getPredators().size();
            }
        }
        return count;
    }

    public int getCountHerbivores() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                count += locations[i][j].getHerbivores().size();
            }
        }
        return count;
    }

    public int getCountAnimals() {
        return getCountPredators() + getCountHerbivores();
    }
}

