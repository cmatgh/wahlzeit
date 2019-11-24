package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SphericCoordinateTest extends AbstractCoordinateTest{

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRadiusCausesException(){
        new SphericCoordinate(-2d, 0d, 0d);
    }

    /**
     *
     */
    @Override
    public void testGettersReturnCorrectValues() {

        SphericCoordinate coordinate = new SphericCoordinate(1d,3d, 2d);
        assertEquals("getRadius returns wrong value",1d, coordinate.getRadius(), DELTA);
        assertEquals("getTheta returns wrong value",3d % Math.PI, coordinate.getTheta(), DELTA);
        assertEquals("getPhi returns wrong value",2d %  (2 *Math.PI), coordinate.getPhi(), DELTA);

        coordinate = new SphericCoordinate(1d,6d,5d);
        assertEquals("getRadius returns wrong value",1d, coordinate.getRadius(), DELTA);
        assertEquals("getTheta returns wrong value",6d % Math.PI, coordinate.getTheta(), DELTA);
        assertEquals("getPhi returns wrong value",5d % (2 *Math.PI), coordinate.getPhi(), DELTA);
    }

    /**
     *
     */
    @Test
    public void testAsCartesianCoordinate(){
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
    public void testAsSphericCoordinate(){
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
    public void testGetCartesianDistance(){
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
    public void testGetCartesianDistanceOnNullThrowsException(){
        SPHERIC_CENTER.getCartesianDistance(null);
    }

    /**
     *
     */
    @Override
    public void testGetCartesianDistanceOnSameObjectReturnsZero(){
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            SphericCoordinate cartesianCoordinate = (SphericCoordinate) CARTESIAN_SPHERIC_PAIRS[i][CoordinateType.SPHERIC.ordinal()];
            assertEquals(0d, cartesianCoordinate.getCartesianDistance(cartesianCoordinate), DELTA);
        }
    }

    /**
     *
     */
    @Override
    public void testGetCentralAngle(){
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
    public void testGetCentralAngleOnNullThrowsException(){
        SPHERIC_CENTER.getCentralAngle(null);
    }

    /**
     *
     */
    @Override
    public void testEqualsOnNull(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnSameObject(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectDifferentValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectSameValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testEqualsOnDifferentObjectType(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        String noCoordinate = new String();
        assertFalse(coordinate.equals(noCoordinate));
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfEqualCoordinates(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    /**
     *
     */
    @Override
    public void testCompareHashCodeOfDifferentCoordinates(){
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = new SphericCoordinate((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = new SphericCoordinate(2d, 1d, (double)i* 2d);
            assertNotEquals(coordinate1.hashCode(), coordinate2.hashCode());
        }
    }


    /**
     *
     */
    @Override
    public void testWhenHashCodesAreEqualEqualsIsEqual(){
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = new SphericCoordinate((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = new SphericCoordinate((double)i* 2d, 1d, 2d);
            assertEquals(coordinate1.equals(coordinate2), coordinate1.hashCode() == coordinate2.hashCode());
        }
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnNull(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.isEqual(null));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnSameObject(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.isEqual(coordinate));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectDifferentValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    /**
     *
     */
    @Override
    public void testIsEqualOnDifferentObjectSameValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }


}
