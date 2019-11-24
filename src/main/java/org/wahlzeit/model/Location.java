package org.wahlzeit.model;

public class Location{

    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Location(Coordinate coordinate){
        setCoordinate(coordinate);
    }

    public void setCoordinate(Coordinate coordinate){
        if(null == coordinate){
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

}