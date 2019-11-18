package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SphericCoordinateTest {

    private final static CartesianCoordinate CARTESIAN_CENTER = new CartesianCoordinate(0d,0d,0d);
    private final static SphericCoordinate SPHERIC_CENTER = new SphericCoordinate(0d,0d,0d);
    private static final double DELTA = 0.0001d;

    private static final Coordinate[][]  CARTESIAN_SPHERIC_PAIRS = new Coordinate[][]{
            { CARTESIAN_CENTER, new SphericCoordinate(0d,0d,0d)},
            { new CartesianCoordinate(-3.594566816, 3.632106035, 1.806459287), new SphericCoordinate(5.42,2.351,1.231)},
            { new CartesianCoordinate(-1426.596824, 158.4175098, 4149.891417),new SphericCoordinate(4391.113,3.031,0.333)},
            { new CartesianCoordinate(-1.32655321, 3.412095991,-2.255627968), new SphericCoordinate(4.3,1.941592654,2.123)},
            { new CartesianCoordinate(0d, 0d, 10d), new SphericCoordinate(10,2 * Math.PI,4 * Math.PI)}
    };

    @Test
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistanceOnNullThrowsException(){
        SPHERIC_CENTER.getCartesianDistance(null);
    }

    @Test
    public void testGetCartesianDistance(){
        Coordinate[][] sphericCoordinatePairs = new Coordinate[][]{
                { SPHERIC_CENTER, SPHERIC_CENTER},
                { CARTESIAN_SPHERIC_PAIRS[1][1], CARTESIAN_SPHERIC_PAIRS[2][1]},
                { CARTESIAN_SPHERIC_PAIRS[1][1], CARTESIAN_SPHERIC_PAIRS[3][1]},
                { CARTESIAN_SPHERIC_PAIRS[2][1], CARTESIAN_SPHERIC_PAIRS[3][1]},
        };

        double[] expected = new double[]{0d, 4388.109246794005d, 4.657557622059717d, 4392.692466627665d};

        for(int i = 0; i < sphericCoordinatePairs.length; i++){
            SphericCoordinate sphericCoordinate1 = (SphericCoordinate) sphericCoordinatePairs[i][0];
            SphericCoordinate sphericCoordinate2 = (SphericCoordinate) sphericCoordinatePairs[i][1];
            assertEquals(expected[i], sphericCoordinate1.getCartesianDistance(sphericCoordinate2), DELTA);
        }
    }

    @Test
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngleOnNullThrowsException(){
        SPHERIC_CENTER.getCentralAngle(null);
    }

    @Test
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
    @Test
    public void equalsOnNull(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Test
    public void equalsOnSameObject(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectDifferentValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectSameValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectType(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        String noCoordinate = new String();
        assertFalse(coordinate.equals(noCoordinate));
    }

    /**
     *
     */
    @Test
    public void compareHashCodeOfEqualCoordinates(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    /**
     *
     */
    @Test
    public void compareHashCodeOfDifferentCoordinates(){
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = new SphericCoordinate((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = new SphericCoordinate(2d, 1d, (double)i* 2d);
            assertNotEquals(coordinate1.hashCode(), coordinate2.hashCode());
        }
    }


    /**
     *
     */
    @Test
    public void whenHashCodesAreEqualEqualsIsEqual(){
        for(int i = 0; i < 10000; i++){
            SphericCoordinate coordinate1 = new SphericCoordinate((double)i* 2d, 2d, 2d);
            SphericCoordinate coordinate2 = new SphericCoordinate((double)i* 2d, 1d, 2d);
            assertEquals(coordinate1.equals(coordinate2), coordinate1.hashCode() == coordinate2.hashCode());
        }
    }

    /**
     *
     */
    @Test
    public void isEqualOnNull(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.isEqual(null));
    }

    /**
     *
     */
    @Test
    public void isEqualOnSameObject(){
        SphericCoordinate coordinate = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.isEqual(coordinate));
    }

    /**
     *
     */
    @Test
    public void isEqualOnDifferentObjectDifferentValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    /**
     *
     */
    @Test
    public void isEqualOnDifferentObjectSameValues(){
        SphericCoordinate coordinate1 = new SphericCoordinate(2d, 2d, 2d);
        SphericCoordinate coordinate2 = new SphericCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }


}
