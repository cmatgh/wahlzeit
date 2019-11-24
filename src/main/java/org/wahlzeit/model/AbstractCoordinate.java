package org.wahlzeit.model;

abstract class AbstractCoordinate implements Coordinate{
    protected static final double DELTA = 0.0001d;

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertNotNull(coordinate);

        return doGetCartesianDistance(coordinate);
    }

    protected abstract double doGetCartesianDistance(Coordinate coordinate);

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertNotNull(coordinate);

        return doGetCentralAngle(coordinate);
    }

    protected abstract double doGetCentralAngle(Coordinate coordinate);

    @Override
    public boolean equals(Object o) {
        if(null == o){
            return false;
        }
        if (this == o){
            return true;
        }
        if (!(o instanceof Coordinate)){
            return false;
        }

        return isEqual((Coordinate) o);
    }

    private void assertNotNull(Coordinate coordinate){
        if(coordinate == null){
            throw new IllegalArgumentException();
        }
    }
}
