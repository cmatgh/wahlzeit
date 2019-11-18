package org.wahlzeit.model;

public class Location{

    private CartesianCoordinate coordinate;

    public CartesianCoordinate getCoordinate() {
        return coordinate;
    }

    public Location(CartesianCoordinate coordinate){
        setCoordinate(coordinate);
    }

    public void setCoordinate(CartesianCoordinate coordinate){
        if(null == coordinate){
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

}