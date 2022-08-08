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

 /*   @Override
    public void run() {
       // System.out.println("startEating " + Thread.currentThread());
        startEating();
      //  System.out.println("startMoving " + Thread.currentThread());
        startMoving();
      //  System.out.println("startBreeding " + Thread.currentThread());
        startBreeding();
     //   System.out.println("startDying " + Thread.currentThread());
        startDying();
    }

    public void startEating() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j].eating();
            }
        }
    }

    public void startMoving() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j].moving(locations);
            }
        }
    }

    public void startBreeding() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j].breeding();
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j].getPredators().forEach(predator -> predator.setBreed(false));
                locations[i][j].getHerbivores().forEach(herbivore -> herbivore.setBreed(false));
            }
        }

    }

    public void startDying() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j].dying();
            }
        }
    }*/

    public int getCountPredators() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                count = count + locations[i][j].getPredators().size();
            }
        }
        return count;
    }

    public int getCountHerbivores() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                count = count + locations[i][j].getHerbivores().size();
            }
        }
        return count;
    }

    public int getCountAnimals() {
        return getCountPredators() + getCountHerbivores();
    }
}

