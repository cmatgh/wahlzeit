package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class CartesianCoordinateTest extends AbstractCoordinateTest{

    @Override
    public void testValueOfReturnsCoordinate() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate != null);
    }

    @Override
    public void testValueOfOnSameValuesReturnSameObject() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(2d, 2d, 2d);

        assertTrue(coordinate1 == coordinate2);
    }

    /**
     *
     */
    @Override
    public void testGettersReturnCorrectValues() throws CoordinateException {
        double x = 14d;
        double y = 542d;
        double z = -556d;
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(x,y,z);
        assertEquals("getX() returns wrong value",x, coordinate.getX(), DELTA);
        assertEquals("getY() returns wrong value",y, coordinate.getY(), DELTA);
        assertEquals("getZ() returns wrong value",z, coordinate.getZ(), DELTA);

        x = 1d;
        y = 2d;
        z = 3d;
        coordinate = CartesianCoordinate.valueOf(x, y,z);
        assertEquals("getX() returns wrong value", x, coordinate.getX(), DELTA);
        assertEquals("getY() returns wrong value", y, coordinate.getY(), DELTA);
        assertEquals("getZ() returns wrong value",z, coordinate.getZ(), DELTA);

    }

    /**
     *
     */
    @Test
    public void testAsCartesianCoordinate() throws CoordinateException {
        Coordinate[] sphericCoordinates = new Coordinate[]{
                CARTESIAN_SPHERIC_PAIRS[1][0],
                CARTESIAN_SPHERIC_PAIRS[2][0],
                CARTESIAN_SPHERIC_PAIRS[3][0],
                CARTESIAN_SPHERIC_PAIRS[3][0],
                CARTESIAN_SPHERIC_PAIRS[4][0],
        };

        for(Coordinate coordinate: sphericCoordinates){
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) coordinate;
            assertTrue(cartesianCoordinate.equals(cartesianCoordinate.asCartesianCoordinate()));
        }

    }

    /**
     *
     */
    @Override
    public void testAsSphericCoordinate() throws CoordinateException {
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) CARTESIAN_SPHERIC_PAIRS[i][CoordinateType.CARTESIAN.ordinal()];
            SphericCoordinate sphericCoordinate = (SphericCoordinate) CARTESIAN_SPHERIC_PAIRS[i][CoordinateType.SPHERIC.ordinal()];
            SphericCoordinate cartesianAsSpheric = cartesianCoordinate.asSphericCoordinate();
            assertEquals(cartesianCoordinate.toString() + " radius", sphericCoordinate.getRadius(), cartesianAsSpheric.getRadius(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " theta", sphericCoordinate.getTheta(), cartesianAsSpheric.getTheta(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " phi", sphericCoordinate.getPhi(), cartesianAsSpheric.getPhi(), DELTA);
        }

    }

    /**
     *
     */
    @Override
    public void testGetCartesianDistance() throws CoordinateException {
        Coordinate[][] coordinatePairs = buildCoordinatePairs(CoordinateType.CARTESIAN);

        for(int i = 0; i < coordinatePairs.length; i++){
            CartesianCoordinate cartesianCoordinate1 = (CartesianCoordinate) coordinatePairs[i][0];
            CartesianCoordinate cartesianCoordinate2 = (CartesianCoordinate) coordinatePairs[i][1];
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
        CARTESIAN_CENTER.getCartesianDistance(null);
    }

    /**
     *
     */
    @Override
    public void testGetCartesianDistanceOnSameObjectReturnsZero() throws CoordinateException {
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) CARTESIAN_SPHERIC_PAIRS[i][CoordinateType.CARTESIAN.ordinal()];
            assertEquals(0d, cartesianCoordinate.getCartesianDistance(cartesianCoordinate), DELTA);
        }
    }

    /**
     *
     */
    @Override
    public void testGetCentralAngle() throws CoordinateException {
        Coordinate[][] sphericCoordinatePairs = new Coordinate[][]{
                { CARTESIAN_CENTER, CARTESIAN_CENTER},
                { CARTESIAN_SPHERIC_PAIRS[1][0], CARTESIAN_SPHERIC_PAIRS[2][0]},
                { CARTESIAN_SPHERIC_PAIRS[1][0], CARTESIAN_SPHERIC_PAIRS[3][0]},
                { CARTESIAN_SPHERIC_PAIRS[2][0], CARTESIAN_SPHERIC_PAIRS[3][0]},
        };

        double[] expected = new double[]{0.0, 0.9828833575640937, 0.9745750728346123, 1.1951280794550139};

        for(int i = 0; i < sphericCoordinatePairs.length; i++){
            CartesianCoordinate cartesianCoordinate1 = (CartesianCoordinate) sphericCoordinatePairs[i][0];
            CartesianCoordinate cartesianCoordinate2 = (CartesianCoordinate) sphericCoordinatePairs[i][1];
            assertEquals(expected[i], cartesianCoordinate1.getCentralAngle(cartesianCoordinate2), DELTA);
        }
    }

    /**
     *
     */
    @Test(expected = CoordinateException.class)
    public void testGetCentralAngleOnNullThrowsException() throws CoordinateException {
        CARTESIAN_CENTER.getCentralAngle(null);
    }



    /**
     *
     */
    @Override
    public void testEqualsOnNull() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnSameObject() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectDifferentValues() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectSameValues() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectType() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        String noCoordinate = new String();
        assertFalse(coordinate.equals(noCoordinate));
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfEqualCoordinates() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfDifferentCoordinates() throws CoordinateException {
        for(int i = 0; i < 10000; i++){
            CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf((double)i* 2d, 2d, 2d);
            CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(2d, 1d, (double)i* 2d);
            assertNotEquals(coordinate1.hashCode(), coordinate2.hashCode());
        }
    }


    /**
     *
     */
    @Override
    public void testWhenHashCodesAreEqualEqualsIsEqual() throws CoordinateException {
        for(int i = 0; i < 10000; i++){
            CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf((double)i* 2d, 2d, 2d);
            CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf((double)i* 2d, 1d, 2d);
            assertEquals(coordinate1.equals(coordinate2), coordinate1.hashCode() == coordinate2.hashCode());
        }
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnNull() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertFalse(coordinate.isEqual(null));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnSameObject() throws CoordinateException {
        CartesianCoordinate coordinate = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate.isEqual(coordinate));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectDifferentValues() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(1d, 2d, 2d);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectSameValues() throws CoordinateException {
        CartesianCoordinate coordinate1 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = CartesianCoordinate.valueOf(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

}