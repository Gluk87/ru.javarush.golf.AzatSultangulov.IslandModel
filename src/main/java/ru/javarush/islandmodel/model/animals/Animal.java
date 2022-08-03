package ru.javarush.islandmodel.model.animals;

import ru.javarush.islandmodel.Application;
import ru.javarush.islandmodel.exceptions.MissingAnnotationException;
import ru.javarush.islandmodel.model.animals.herbivores.*;
import ru.javarush.islandmodel.model.animals.predators.*;
import ru.javarush.islandmodel.model.animals.predators.Predator;
import ru.javarush.islandmodel.model.island.Direction;
import ru.javarush.islandmodel.model.island.Location;
import ru.javarush.islandmodel.model.plants.Plant;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unchecked")
public abstract class Animal {
    public static final int COUNT_TRIES_TO_MOVE = 10;
    private static final Map<Class<? extends Animal>, Map<Class<? extends Animal>, Integer>> CHANCE_TO_EAT =
            Map.of(Wolf.class, Map.of(Deer.class, 15, Rabbit.class, 60, Mouse.class, 80, Goat.class, 60,
                            Sheep.class, 70, Boar.class, 15, Buffalo.class, 10, Duck.class, 40),
                    Boa.class, Map.of(Fox.class, 15, Rabbit.class, 20, Mouse.class, 40, Duck.class, 10),
                    Fox.class, Map.of(Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Caterpillar.class, 40),
                    Bear.class, Map.of(Boa.class, 80, Horse.class, 40, Deer.class, 80, Rabbit.class, 80,
                             Mouse.class, 90, Goat.class, 70, Sheep.class, 70, Boar.class, 50,
                             Buffalo.class, 20, Duck.class, 70),
                    Eagle.class, Map.of(Fox.class, 10, Rabbit.class, 90, Mouse.class, 90, Duck.class, 80),
                    Boar.class, Map.of(Mouse.class, 50, Caterpillar.class, 80),
                    Mouse.class, Map.of(Caterpillar.class, 90),
                    Duck.class, Map.of(Caterpillar.class, 90));

    public abstract double getSatiety();

    public abstract void setSatiety(double satiety);

    public abstract Gender getGender();

    public abstract boolean isBreed();

    public abstract void setBreed(boolean isBreed);

    public abstract void breed(Location location);

    public void eat(List<?> objects) {
        if (!objects.isEmpty()) {
            if (objects.get(0) instanceof Animal) {
                eatAnimal((List<? extends Animal>) objects);
            } else if (objects.get(0) instanceof Plant) {
                eatPlant((List<Plant>) objects);
            }
        }
    }

    public boolean move(Location currentLocation, Location[][] locations) {
        Location newLocation = null;
        int tries = 0;
        while (tries < COUNT_TRIES_TO_MOVE) {
            newLocation = getNewLocation(currentLocation, locations);
            if (newLocation != null) {
                break;
            }
            tries++;
        }
        if (newLocation != null) {
            newLocation.addAnimal(this);
            return true;
        } else {
            return false;
        }
    }

    public boolean die() {
        return this.getSatiety() == 0;
    }

    protected Gender getRandomGender() {
        return Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)];
    }

    protected double getMaxSatiety() {
        return this.getCharacteristics().maxSatiety();
    }

    private double getWeight() {
        return this.getCharacteristics().weight();
    }

    private int getMaxOnOneLocation() {
        return this.getCharacteristics().maxOnOneLocation();
    }

    private int getPossibleDistance() {
        return this.getCharacteristics().possibleDistance();
    }

    private Characteristics getCharacteristics() {
        if (!this.getClass().isAnnotationPresent(Characteristics.class)){
            throw new MissingAnnotationException("Missing annotation " + Characteristics.class +" for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(Characteristics.class);
    }

    private Location getNewLocation(Location currentLocation, Location[][] locations) {

        int steps = getRandomSteps();
        int oldX = currentLocation.getCoordinates().getX();
        int oldY = currentLocation.getCoordinates().getY();
        int lengthIsland = getBorderIslandX();
        int widthIsland = getBorderIslandY();
        Direction direction = getRandomDirection(oldX, oldY, lengthIsland, widthIsland);
        int newX = getNewCoordinateX(direction, oldX, steps);
        int newY = getNewCoordinateY(direction, oldY, steps);
        if (!isValidCoordinates(newX, newY, lengthIsland, widthIsland)) {
            return null;
        }

        Location newLocation = locations[newX][newY];
        if (!isLocationFree(newLocation)) {
            return null;
        }
        return newLocation;
    }

    private int getNewCoordinateX(Direction direction, int oldX, int steps) {
        int newX = 0;
        if (direction == Direction.UP) {
            newX = oldX - steps;
        }
        else if (direction == Direction.DOWN) {
            newX = oldX + steps;
        }
        return newX;
    }

    private int getNewCoordinateY(Direction direction, int oldY, int steps) {
        int newY = 0;
        if (direction == Direction.LEFT) {
            newY = oldY - steps;
        }
        else if (direction == Direction.RIGHT) {
            newY = oldY + steps;
        }
        return newY;
    }

    private boolean isLocationFree(Location location) {
        if (this instanceof Herbivore) {
            List<Herbivore> herbivores = location.getHerbivores();
            int countHerbivoreOnNewLocation = (int) herbivores.stream().filter(this::equals).count();
            return countHerbivoreOnNewLocation < getMaxOnOneLocation();
        }else if (this instanceof Predator) {
            List<Predator> predators = location.getPredators();
            int countPredatorOnNewLocation = (int) predators.stream().filter(this::equals).count();
            return countPredatorOnNewLocation < getMaxOnOneLocation();
        } else {
            return false;
        }
    }

    private boolean isValidCoordinates(int newX, int newY, int lengthIsland, int widthIsland) {
        return newX <= lengthIsland - 1 && newY <= widthIsland - 1 && newX >= 0 && newY >= 0;
    }

    private Direction getRandomDirection(int oldX, int oldY, int lengthIsland, int widthIsland) {
        //TODO
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    private int getRandomSteps() {
        int possibleDistance = getPossibleDistance();
        if (possibleDistance == 1) {
            return possibleDistance;
        } else {
            return ThreadLocalRandom.current().nextInt(1, possibleDistance);
        }
    }

    private int getBorderIslandX() {

        //TODO
        return Integer.parseInt(getProperties().getProperty("length"));
    }

    private int getBorderIslandY() {
        return Integer.parseInt(getProperties().getProperty("width"));
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try(FileReader fileReader = new FileReader(Objects.requireNonNull(Application.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void eatAnimal(List<? extends Animal> animals) {
        Iterator<? extends Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            Integer chanceToEat = CHANCE_TO_EAT.get(this.getClass()).get(animal.getClass());
            if (chanceToEat != null) {
                int random = ThreadLocalRandom.current().nextInt(1, 100);
                if (chanceToEat >= random && this.getSatiety() < this.getMaxSatiety()) {
                    this.setSatiety(Math.min(this.getSatiety() + animal.getWeight(), this.getMaxSatiety()));
                    iterator.remove();
                    return;
                }
            }

        }
    }

    private void eatPlant(List<Plant> plants) {
        Iterator<Plant> iterator = plants.iterator();
        while (iterator.hasNext()) {
            Plant plant = iterator.next();
            if (this.getSatiety() < this.getMaxSatiety()) {
                this.setSatiety(Math.min(this.getSatiety() + plant.getWeight(), this.getMaxSatiety()));
                iterator.remove();
                return;
            }
        }
    }
}

