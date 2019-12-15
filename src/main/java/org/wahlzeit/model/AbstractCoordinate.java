package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

abstract class AbstractCoordinate implements Coordinate{

    protected static final double DELTA = 0.0001d;

    protected static final Logger log = Logger.getLogger(LandscapePhotoManager.class.getName());

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws CoordinateException {
        try {
            assertClassInvariants();

            CartesianCoordinate cartesianCoordinate = this.doAsCartesianCoordinate();

            assert cartesianCoordinate != null;

            assertClassInvariants();
            return cartesianCoordinate;
        }catch (Exception ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new CoordinateException(ex.getMessage());
        }
    }

    protected abstract CartesianCoordinate doAsCartesianCoordinate() throws CoordinateException;

    @Override
    public SphericCoordinate asSphericCoordinate() throws CoordinateException {
        try {
            assertClassInvariants();

            SphericCoordinate sphericCoordinate = this.doAsSphericCoordinate();

            assert sphericCoordinate != null;

            assertClassInvariants();
            return sphericCoordinate;
        }catch (Exception ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new CoordinateException(ex.getMessage());
        }
    }

    protected abstract SphericCoordinate doAsSphericCoordinate() throws CoordinateException;

    @Override
    public double getCartesianDistance(Coordinate coordinate) throws CoordinateException {
        try{
            assertClassInvariants();

            assertNotNull(coordinate);

            double distance = doGetCartesianDistance(coordinate);

            assert distance >= 0;

            assertClassInvariants();

            return distance;
        }catch (Exception ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new CoordinateException(ex.getMessage());
        }
    }

    protected abstract double doGetCartesianDistance(Coordinate coordinate) throws CoordinateException;

    @Override
    public double getCentralAngle(Coordinate coordinate) throws CoordinateException {
        try{
            assertClassInvariants();
            assertNotNull(coordinate);

            double angle = doGetCentralAngle(coordinate);

            assert angle >= 0;
            assertClassInvariants();

            return angle;
        }catch (Exception ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new CoordinateException(ex.getMessage());
        }

    }

    protected abstract double doGetCentralAngle(Coordinate coordinate) throws CoordinateException;

    @Override
    public boolean isEqual(Coordinate coordinate) throws CoordinateException {
        try {
            assertClassInvariants();

            if (null == coordinate) {
                return false;
            }
            if (this == coordinate) {
                return true;
            }

            boolean equal = doIsEqual(coordinate);

            assertClassInvariants();

            return equal;
        }catch (Exception ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new CoordinateException(ex.getMessage());
        }

    }

    protected abstract boolean doIsEqual(Coordinate coordinate) throws CoordinateException;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Coordinate)){
            return false;
        }
        if (this == o){
            return true;
        }

        try {
            return isEqual((Coordinate) o);
        } catch (CoordinateException ex) {
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            return false;
        }
    }

    private void assertNotNull(Coordinate coordinate){
        if(coordinate == null){
            throw new IllegalArgumentException();
        }
    }

    abstract protected void assertClassInvariants();
}
