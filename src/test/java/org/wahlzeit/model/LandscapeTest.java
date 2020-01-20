
package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LandscapeTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullParameterThrowsException(){
        new Landscape(null);
    }

    @Test
    public void testConstructorSetsLandscapeType(){
        LandscapeType landscapeType = LandscapeType.valueOf("SomeType");
        Landscape landscape = new Landscape(landscapeType);

        assertEquals(landscapeType, landscape.getLandscapeType());
    }
}