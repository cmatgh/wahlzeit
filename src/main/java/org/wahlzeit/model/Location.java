package org.wahlzeit.model;

public class Location{

    public Coordinate coordinate;

    public Location(Coordinate coordinate){
        if(null == coordinate){
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

}