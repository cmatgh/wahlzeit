package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SphericCoordinateTest extends AbstractCoordinateTest{

    @Override
    public void testValueOfReturnsCoordinate() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate != null);
    }

    @Override
    public void testValueOfOnSameValuesReturnSameObject() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(2d, 2d, 2d);

        assertTrue(coordinate1 == coordinate2);
    }

    /**
     *
     */
    @Test(expected = CoordinateException.class)
    public void testNegativeRadiusCausesException() throws CoordinateException {
        SphericCoordinate.valueOf(-2d, 0d, 0d);
    }

    /**
     *
     */
    @Override
    public void testGettersReturnCorrectValues() throws CoordinateException {

        double r  = 1d;
        double theta = 3d;
        double phi = 2d;
        SphericCoordinate coordinate = SphericCoordinate.valueOf(r,theta, phi);
        assertEquals("getRadius returns wrong value",r, coordinate.getRadius(), DELTA);
        assertEquals("getTheta returns wrong value",theta % Math.PI, coordinate.getTheta(), DELTA);
        assertEquals("getPhi returns wrong value",phi %  (2 *Math.PI), coordinate.getPhi(), DELTA);

        r = 1d;
        theta = 6d;
        phi = 5d;
        coordinate = SphericCoordinate.valueOf(r,theta,phi);
        assertEquals("getRadius returns wrong value",r, coordinate.getRadius(), DELTA);
        assertEquals("getTheta returns wrong value",theta % Math.PI, coordinate.getTheta(), DELTA);
        assertEquals("getPhi returns wrong value",phi % (2 *Math.PI), coordinate.getPhi(), DELTA);

        r = 1d;
        theta = -1d;
        phi = -2d;
        coordinate = SphericCoordinate.valueOf(r,theta,phi);
        assertEquals("getRadius returns wrong value",r, coordinate.getRadius(), DELTA);
        assertEquals("getTheta returns wrong value",theta + Math.PI, coordinate.getTheta(), DELTA);
        assertEquals("getPhi returns wrong value",phi + (2d * Math.PI), coordinate.getPhi(), DELTA);
    }

    /**
     *
     */
    @Test
    public void testAsCartesianCoordinate() throws CoordinateException {
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) CARTESIAN_SPHERIC_PAIRS[i][0];
            SphericCoordinate sphericCoordinate = (SphericCoordinate) CARTESIAN_SPHERIC_PAIRS[i][1];
            CartesianCoordinate sphericAsCartesian = sphericCoordinate.asCartesianCoordinate();
            assertEquals(cartesianCoordinate.toString() + " x", cartesianCoordinate.getX(), sphericAsCartesian.getX(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " y", cartesianCoordinate.getY(), sphericAsCartesian.getY(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " z", cartesianCoordinate.getZ(), sphericAsCartesian.getZ(), DELTA);
        }

    }

    /**
     *
     */
    @Override
    public void testAsSphericCoordinate() throws CoordinateException {
        Coordinate[] sphericCoordinates = new Coordinate[]{
                CARTESIAN_SPHERIC_PAIRS[1][1],
                CARTESIAN_SPHERIC_PAIRS[2][1],
                CARTESIAN_SPHERIC_PAIRS[3][1],
                CARTESIAN_SPHERIC_PAIRS[3][1],
                CARTESIAN_SPHERIC_PAIRS[4][1],
        };

        for(Coordinate coordinate: sphericCoordinates){
            SphericCoordinate sphericCoordinate = (SphericCoordinate) coordinate;
            assertTrue(sphericCoordinate.equals(sphericCoordinate.asSphericCoordinate()));
        }

    }

    /**
     *
     */
    @Override
    public void testGetCartesianDistance() throws CoordinateException {
        Coordinate[][] coordinatePairs = buildCoordinatePairs(CoordinateType.SPHERIC);

        for(int i = 0; i < coordinatePairs.length; i++){
            SphericCoordinate cartesianCoordinate1 = (SphericCoordinate) coordinatePairs[i][0];
            SphericCoordinate cartesianCoordinate2 = (SphericCoordinate) coordinatePairs[i][1];
            assertEquals(calculateCartesianDistance(cartesianCoordinate1, cartesianCoordinate2)
                    , cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2)
                    , DELTA);
        }
    }


    /**
     *
     */
    @Override
    public void testGetCartesianDistanceOnNullThrowsException() throws CoordinateException {
        SPHERIC_CENTER.getCartesianDistance(null);
    }

    /**
     *
     */
    @Override
    public void testGetCartesianDistanceOnSameObjectReturnsZero() throws CoordinateException {
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            SphericCoordinate cartesianCoordinate = (SphericCoordinate) CARTESIAN_SPHERIC_PAIRS[i][CoordinateType.SPHERIC.ordinal()];
            assertEquals(0d, cartesianCoordinate.getCartesianDistance(cartesianCoordinate), DELTA);
        }
    }

    /**
     *
     */
    @Override
    public void testGetCentralAngle() throws CoordinateException {
        Coordinate[][] sphericCoordinatePairs = new Coordinate[][]{
                { SPHERIC_CENTER, SPHERIC_CENTER},
                { CARTESIAN_SPHERIC_PAIRS[1][1], CARTESIAN_SPHERIC_PAIRS[2][1]},
                { CARTESIAN_SPHERIC_PAIRS[1][1], CARTESIAN_SPHERIC_PAIRS[3][1]},
                { CARTESIAN_SPHERIC_PAIRS[2][1], CARTESIAN_SPHERIC_PAIRS[3][1]},
        };

        double[] expected = new double[]{0.0, 0.9828833575640937, 0.9745750728346124, 1.1951280794550139};

        for(int i = 0; i < sphericCoordinatePairs.length; i++){
            SphericCoordinate sphericCoordinate1 = (SphericCoordinate) sphericCoordinatePairs[i][0];
            SphericCoordinate sphericCoordinate2 = (SphericCoordinate) sphericCoordinatePairs[i][1];
            assertEquals(expected[i], sphericCoordinate1.getCentralAngle(sphericCoordinate2), DELTA);
        }
    }

    /**
     *
     */
    @Override
    public void testGetCentralAngleOnNullThrowsException() throws CoordinateException {
        SPHERIC_CENTER.getCentralAngle(null);
    }

    /**
     *
     */
    @Override
    public void testEqualsOnNull() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnSameObject() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectDifferentValues() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectSameValues() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectType() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        String noCoordinate = new String();
        assertFalse(coordinate.equals(noCoordinate));
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfEqualCoordinates() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfDifferentCoordinates() throws CoordinateException {
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = SphericCoordinate.valueOf((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = SphericCoordinate.valueOf(2d, 1d, (double)i* 2d);
            assertNotEquals(coordinate1.hashCode(), coordinate2.hashCode());
        }
    }


    /**
     *
     */
    @Override
    public void testWhenHashCodesAreEqualEqualsIsEqual() throws CoordinateException {
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = SphericCoordinate.valueOf((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = SphericCoordinate.valueOf((double)i* 2d, 1d, 2d);
            assertEquals(coordinate1.equals(coordinate2), coordinate1.hashCode() == coordinate2.hashCode());
        }
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnNull() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertFalse(coordinate.isEqual(null));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnSameObject() throws CoordinateException {
        SphericCoordinate coordinate = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate.isEqual(coordinate));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectDifferentValues() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(1d, 2d, 2d);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectSameValues() throws CoordinateException {
        SphericCoordinate coordinate1 = SphericCoordinate.valueOf(2d, 2d, 2d);
        SphericCoordinate coordinate2 = SphericCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }


}
