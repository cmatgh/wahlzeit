package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractCoordinateTest {

    protected enum CoordinateType{
        CARTESIAN,
        SPHERIC
    };

    protected final static CartesianCoordinate CARTESIAN_CENTER = new CartesianCoordinate(0d,0d,0d);
    protected static SphericCoordinate SPHERIC_CENTER = new SphericCoordinate(0d, 0d, 0d);


    protected static final double DELTA = 0.0001d;
    protected static Coordinate[][]  CARTESIAN_SPHERIC_PAIRS = new Coordinate[][]{
                   { CARTESIAN_CENTER, SPHERIC_CENTER},
                   { new CartesianCoordinate(-3.594566816, 3.632106035, 1.806459287), new SphericCoordinate(5.42,2.351,1.231)},
                   { new CartesianCoordinate(-1426.596824, 158.4175098, 4149.891417),new SphericCoordinate(4391.113,3.031,0.333)},
                   { new CartesianCoordinate(-1.32655321, 3.412095991,-2.255627968), new SphericCoordinate(4.3,1.941592654,2.123)},
                   { new CartesianCoordinate(0d, 0d, 10d), new SphericCoordinate(10,2 * Math.PI,4 * Math.PI)},
                   { new CartesianCoordinate(13.33653951, 63.28842394,372.425445), new SphericCoordinate(378,133.31,44.15425)}
        };


    @Test
    public abstract void testGettersReturnCorrectValues();

    @Test
    public abstract void testAsCartesianCoordinate() throws CoordinateException;

    @Test
    public abstract void testAsSphericCoordinate() throws CoordinateException;

    @Test
    public abstract void testGetCartesianDistance() throws CoordinateException;

    @Test(expected = CoordinateException.class)
    public abstract void testGetCartesianDistanceOnNullThrowsException() throws CoordinateException;

    @Test
    public abstract void testGetCartesianDistanceOnSameObjectReturnsZero() throws CoordinateException;

    @Test
    public abstract void testGetCentralAngle() throws CoordinateException;

    @Test(expected = CoordinateException.class)
    public abstract void testGetCentralAngleOnNullThrowsException() throws CoordinateException;

    @Test
    public abstract void testEqualsOnNull();

    @Test
    public abstract void testEqualsOnSameObject();

    @Test
    public abstract void testEqualsOnDifferentObjectDifferentValues();

    @Test
    public abstract void testEqualsOnDifferentObjectSameValues();

    @Test
    public abstract void testEqualsOnDifferentObjectType();

    @Test
    public abstract void testCompareHashCodeOfEqualCoordinates();

    @Test
    public abstract void testCompareHashCodeOfDifferentCoordinates();

    @Test
    public abstract void testWhenHashCodesAreEqualEqualsIsEqual();

    @Test
    public abstract void testIsEqualOnNull() throws CoordinateException;

    @Test
    public abstract void testIsEqualOnSameObject() throws CoordinateException;

    @Test
    public abstract void testIsEqualOnDifferentObjectDifferentValues() throws CoordinateException;

    @Test
    public abstract void testIsEqualOnDifferentObjectSameValues();


    protected Coordinate[][] buildCoordinatePairs(CoordinateType type){
        int length = (CARTESIAN_SPHERIC_PAIRS.length * (CARTESIAN_SPHERIC_PAIRS.length - 1))/2;
        Coordinate[][] coordinatePairs = new Coordinate[length][2];

        int index = 0;
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            for(int j = i + 1; j < CARTESIAN_SPHERIC_PAIRS.length; j++){
                coordinatePairs[index] = new Coordinate[] {CARTESIAN_SPHERIC_PAIRS[i][type.ordinal()], CARTESIAN_SPHERIC_PAIRS[j][type.ordinal()]};
                index++;
            }
        }
        return coordinatePairs;
    }

    protected double calculateCartesianDistance(Coordinate c1, Coordinate c2) throws CoordinateException {
        CartesianCoordinate c1Cart = c1.asCartesianCoordinate();
        CartesianCoordinate c2Cart = c2.asCartesianCoordinate();

        return Math.sqrt(Math.pow((c1Cart.getX() - c2Cart.getX()),2) + Math.pow((c1Cart.getY() - c2Cart.getY()),2) + Math.pow((c1Cart.getZ() - c2Cart.getZ()),2));
    }

}
