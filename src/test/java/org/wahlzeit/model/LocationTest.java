package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LocationTest {

    Coordinate coordinate = null;

    @Before
    public void setup() throws Exception {
        coordinate = new Coordinate(1d,1d,1d);
    }

    /**
     *
     */
    @Test
    public void testConstructorWithValidParameter() {
        Location location = new Location(coordinate);

        assertTrue(coordinate.equals(location.getCoordinate()));
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionOnNull() {
        Location location = new Location(null);
    }

    /**
     *
     */
    @Test
    public void testSetCoordinate() {
        Location location = new Location(coordinate);
        location.setCoordinate(coordinate);

        assertTrue(coordinate.equals(location.getCoordinate()));
    }

}
