package org.wahlzeit.model;

public interface Coordinate {

    CartesianCoordinate asCartesianCoordinate() throws CoordinateException;
    double getCartesianDistance(Coordinate coordinate) throws CoordinateException;
    SphericCoordinate asSphericCoordinate() throws CoordinateException;
    double getCentralAngle(Coordinate coordinate) throws CoordinateException;
    boolean isEqual(Coordinate coordinate) throws CoordinateException;

}
