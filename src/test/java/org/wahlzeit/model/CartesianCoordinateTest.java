package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class CartesianCoordinateTest {

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
        CartesianCoordinate coordinate = new CartesianCoordinate(1d,2d,3d);
        assertEquals("getRadius returns wrong value",1d, coordinate.getX(), DELTA);
        assertEquals("getPhi returns wrong value",2d, coordinate.getY(), DELTA);
        assertEquals("getTheta returns wrong value",3d, coordinate.getZ(), DELTA);

        coordinate = new CartesianCoordinate(1d,5d,6d);
        assertEquals("getRadius returns wrong value",1d, coordinate.getX(), DELTA);
        assertEquals("getPhi returns wrong value",5d % (2 * Math.PI), coordinate.getY(), DELTA);
        assertEquals("getTheta returns wrong value",6d % (2 * Math.PI), coordinate.getZ(), DELTA);
    }

    @Test
    public void testAsSphericalCoordinate(){
        for(int i = 0; i < CARTESIAN_SPHERIC_PAIRS.length; i++){
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) CARTESIAN_SPHERIC_PAIRS[i][0];
            SphericCoordinate sphericCoordinate = (SphericCoordinate) CARTESIAN_SPHERIC_PAIRS[i][1];
            SphericCoordinate cartesianAsSpheric = cartesianCoordinate.asSphericCoordinate();
            assertEquals(cartesianCoordinate.toString() + " radius", sphericCoordinate.getRadius(), cartesianAsSpheric.getRadius(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " theta", sphericCoordinate.getTheta(), cartesianAsSpheric.getTheta(), DELTA);
            assertEquals(cartesianCoordinate.toString() + " phi", sphericCoordinate.getPhi(), cartesianAsSpheric.getPhi(), DELTA);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistanceOnNullThrowsException(){
        CARTESIAN_CENTER.getCartesianDistance(null);
    }

    @Test
    public void testGetCartesianDistance(){
        Coordinate[][] cartesianCoordinatePairs = new Coordinate[][]{
                { CARTESIAN_CENTER, CARTESIAN_CENTER},
                { CARTESIAN_SPHERIC_PAIRS[1][0], CARTESIAN_SPHERIC_PAIRS[2][0]},
                { CARTESIAN_SPHERIC_PAIRS[1][0], CARTESIAN_SPHERIC_PAIRS[3][0]},
                { CARTESIAN_SPHERIC_PAIRS[2][0], CARTESIAN_SPHERIC_PAIRS[3][0]}
        };

        double[] expected = new double[]{0d, 4388.109246794005d, 4.657557622059717d, 4392.692466627665d};

        for(int i = 0; i < cartesianCoordinatePairs.length; i++){
            CartesianCoordinate cartesianCoordinate1 = (CartesianCoordinate) cartesianCoordinatePairs[i][0];
            CartesianCoordinate cartesianCoordinate2 = (CartesianCoordinate) cartesianCoordinatePairs[i][1];
            assertEquals(expected[i], cartesianCoordinate1.getCartesianDistance(cartesianCoordinate2), DELTA);
        }
    }

    @Test
    public void testAsCartesianCoordinate(){
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngleOnNullThrowsException(){
        CARTESIAN_CENTER.getCentralAngle(null);
    }

    @Test
    public void testGetCentralAngle(){
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
    @Test
    public void gettersReturnCorrectValues() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1d,2d,3d);
        assertEquals("getX returns wrong value",1d, coordinate.getX(), .001d);
        assertEquals("getY returns wrong value",2d, coordinate.getY(), .001d);
        assertEquals("getZ returns wrong value",3d, coordinate.getZ(), .001d);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullOnGetCartesianDistanceCausesException() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1d,2d,3d);
        coordinate.getCartesianDistance(null);
    }

    /**
     *
     */
    @Test
    public void equalsOnNull(){
        CartesianCoordinate coordinate = new CartesianCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Test
    public void equalsOnSameObject(){
        CartesianCoordinate coordinate = new CartesianCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectDifferentValues(){
        CartesianCoordinate coordinate1 = new CartesianCoordinate(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectSameValues(){
        CartesianCoordinate coordinate1 = new CartesianCoordinate(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectType(){
        CartesianCoordinate coordinate = new CartesianCoordinate(2d, 2d, 2d);
        String noCoordinate = new String();
        assertFalse(coordinate.equals(noCoordinate));
    }

    /**
     *
     */
    @Test
    public void compareHashCodeOfEqualCoordinates(){
        CartesianCoordinate coordinate1 = new CartesianCoordinate(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(2d, 2d, 2d);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    /**
     *
     */
    @Test
    public void compareHashCodeOfDifferentCoordinates(){
        for(int i = 0; i < 10000; i++){
            CartesianCoordinate coordinate1 = new CartesianCoordinate((double)i* 2d, 2d, 2d);
            CartesianCoordinate coordinate2 = new CartesianCoordinate(2d, 1d, (double)i* 2d);
            assertNotEquals(coordinate1.hashCode(), coordinate2.hashCode());
        }
    }


    /**
     *
     */
    @Test
    public void whenHashCodesAreEqualEqualsIsEqual(){
        for(int i = 0; i < 10000; i++){
            CartesianCoordinate coordinate1 = new CartesianCoordinate((double)i* 2d, 2d, 2d);
            CartesianCoordinate coordinate2 = new CartesianCoordinate((double)i* 2d, 1d, 2d);
            assertEquals(coordinate1.equals(coordinate2), coordinate1.hashCode() == coordinate2.hashCode());
        }
    }

    /**
     *
     */
    @Test
    public void isEqualOnNull(){
        CartesianCoordinate coordinate = new CartesianCoordinate(2d, 2d, 2d);
        assertFalse(coordinate.isEqual(null));
    }

    /**
     *
     */
    @Test
    public void isEqualOnSameObject(){
        CartesianCoordinate coordinate = new CartesianCoordinate(2d, 2d, 2d);
        assertTrue(coordinate.isEqual(coordinate));
    }

    /**
     *
     */
    @Test
    public void isEqualOnDifferentObjectDifferentValues(){
        CartesianCoordinate coordinate1 = new CartesianCoordinate(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(1d, 2d, 2d);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    /**
     *
     */
    @Test
    public void isEqualOnDifferentObjectSameValues(){
        CartesianCoordinate coordinate1 = new CartesianCoordinate(2d, 2d, 2d);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

}