package ru.javarush.model.animals;

import ru.javarush.Application;
import ru.javarush.exceptions.MissingAnnotationException;
import ru.javarush.model.animals.herbivores.Herbivore;
import ru.javarush.model.animals.predators.Predator;
import ru.javarush.model.island.Direction;
import ru.javarush.system.Chances;
import ru.javarush.model.island.Location;
import ru.javarush.model.plants.Plant;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unchecked")
public abstract class Animal {
    public static final int COUNT_TRIES_TO_MOVE = 10;

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

    public boolean move(Location location) {
        Location newLocation = null;
        int tries = 0;
        while (tries < COUNT_TRIES_TO_MOVE) {
            newLocation = getNewLocation(location);
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

    private Location getNewLocation(Location location) {
        Direction direction = getRandomDirection();
        int steps = getRandomSteps();
        int oldX = location.getCoordinates().getX();
        int oldY = location.getCoordinates().getY();
        int newX = getNewCoordinateX(direction, oldX, steps);
        int newY = getNewCoordinateY(direction, oldY, steps);
        int lengthIsland = getBorderIslandX();
        int widthIsland = getBorderIslandY();
        if (!isValidCoordinates(newX, newY, lengthIsland, widthIsland)) {
            return null;
        }
        Location newLocation = null; //TODO location..getLocations()[newX][newY];
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

    private Direction getRandomDirection() {
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
            int chanceToEat = Chances.getInstance().getChanceToEat(this, animal);
            int random = ThreadLocalRandom.current().nextInt(1, 100);
            if (chanceToEat >= random && this.getSatiety() < this.getMaxSatiety()) {
                this.setSatiety(Math.min(this.getSatiety() + animal.getWeight(), this.getMaxSatiety()));
                iterator.remove();
                return;
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

