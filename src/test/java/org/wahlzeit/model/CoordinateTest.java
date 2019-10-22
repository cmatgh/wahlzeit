package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class CoordinateTest{

    /**
     *
     */
    @Test
    public void gettersReturnCorrectValues() {
        Coordinate coordinate = new Coordinate(1d,2d,3d);
        assertEquals("getX returns wrong value",1d, coordinate.getX(), .001d);
        assertEquals("getY returns wrong value",2d, coordinate.getY(), .001d);
        assertEquals("getZ returns wrong value",3d, coordinate.getZ(), .001d);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullOnGetDistanceCausesException() {
        Coordinate coordinate = new Coordinate(1d,2d,3d);
        coordinate.getDistance(null);
    }

    /**
     *
     */
    @Test
    public void equalsOnNull(){
        Coordinate coordinate = new Coordinate(2d, 2d, 2d);
        assertFalse(coordinate.equals(null));
    }

    /**
     *
     */
    @Test
    public void equalsOnSameObject(){
        Coordinate coordinate = new Coordinate(2d, 2d, 2d);
        assertTrue(coordinate.equals(coordinate));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectDifferentValues(){
        Coordinate coordinate1 = new Coordinate(2d, 2d, 2d);
        Coordinate coordinate2 = new Coordinate(1d, 2d, 2d);
        assertFalse(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void equalsOnDifferentObjectSameValues(){
        Coordinate coordinate1 = new Coordinate(2d, 2d, 2d);
        Coordinate coordinate2 = new Coordinate(2d, 2d, 2d);
        assertTrue(coordinate1.equals(coordinate2));
    }

    /**
     *
     */
    @Test
    public void getDistanceCalculatesCorrectly(){
        assertDistances(1d,2d,3d, 5d, 2d, 1d);
        assertDistances(-5.123d,2d,933d, -11d, 0.001d, 1d);
    }

    /**
     *
     */
    private void assertDistances(double x1,double y1,double z1,double x2,double y2,double z2){
        Coordinate coordinate1 = new Coordinate(x1,y1,z1);
        Coordinate coordinate2 = new Coordinate(x2,y2,z2);
        assertEquals("getDistance calculation is wrong", calculateDistance(coordinate1, coordinate2), coordinate1.getDistance(coordinate2), .001d );
    }

    /**
     *
     */
    private double calculateDistance(Coordinate coordinate1, Coordinate coordinate2){
        double dx = coordinate1.getX() - coordinate2.getX();
        double dy = coordinate1.getY() - coordinate2.getY();
        double dz = coordinate1.getZ() - coordinate2.getZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

}