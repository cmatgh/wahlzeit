package org.wahlzeit.model;

abstract class AbstractCoordinate implements Coordinate{
    static final double DELTA = 0.0001d;

    @Override
    public CartesianCoordinate asCartesianCoordinate(){
        assertClassInvariants();

        CartesianCoordinate cartesianCoordinate = this.doAsCartesianCoordinate();

        assert cartesianCoordinate != null;

        assertClassInvariants();
        return cartesianCoordinate;
    }

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    @Override
    public SphericCoordinate asSphericCoordinate(){
        assertClassInvariants();

        SphericCoordinate sphericCoordinate = this.doAsSphericCoordinate();

        assert sphericCoordinate != null;

        assertClassInvariants();
        return sphericCoordinate;
    }

    protected abstract SphericCoordinate doAsSphericCoordinate();

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariants();

        assertNotNull(coordinate);

        double distance = doGetCartesianDistance(coordinate);

        assert distance >= 0;

        assertClassInvariants();

        return distance;
    }

    protected abstract double doGetCartesianDistance(Coordinate coordinate);

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();

        assertNotNull(coordinate);

        double angle = doGetCentralAngle(coordinate);

        assert angle >= 0;

        assertClassInvariants();

        return angle;
    }

    protected abstract double doGetCentralAngle(Coordinate coordinate);

    @Override
    public boolean isEqual(Coordinate coordinate){
        assertClassInvariants();

        if(null == coordinate){
            return false;
        }
        if(this == coordinate){
            return true;
        }

        boolean equal = doIsEqual(coordinate);

        assertClassInvariants();

        return equal;
    }

    protected abstract boolean doIsEqual(Coordinate coordinate);

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

    abstract protected void assertClassInvariants();
}
