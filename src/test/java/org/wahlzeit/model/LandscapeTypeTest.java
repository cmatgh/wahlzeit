package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LandscapeTypeTest {

    @Test(expected = NullPointerException.class)
    public void testDoValueOfOnNullThrowsException(){
        LandscapeType landscapeType = LandscapeType.valueOf(null);
    }

    @Test
    public void testDoValueOfOnNewType(){
        String name = "NewType";
        LandscapeType landscapeType = LandscapeType.valueOf(name);

        assertNotNull(landscapeType);
        assertEquals(name, landscapeType.getName());
    }

    @Test
    public void testDoValueOfOnSameType(){
        String name = "NewType";
        LandscapeType landscapeType = LandscapeType.valueOf(name);

        assertNotNull(landscapeType);
        assertEquals(name, landscapeType.getName());
        assertTrue(landscapeType == landscapeType.valueOf(name));
    }

    @Test
    public void testIsSubTypeOnNonSubType(){
        String name = "NewType";
        LandscapeType landscapeType = LandscapeType.valueOf(name);

        assertFalse(landscapeType.isSubType());
    }

    @Test
    public void testIsSubTypeOnSubType(){
        String typeName = "NewType";
        LandscapeType landscapeType = LandscapeType.valueOf(typeName);
        String subTypeName = "SubType";
        LandscapeType landscapeSubType = LandscapeType.valueOf(subTypeName);
        landscapeType.addSubType(landscapeSubType);

        assertFalse(landscapeType.isSubType());
        assertTrue(landscapeSubType.isSubType());
    }

    @Test
    public void testCreateInstance(){
        String name = "SomeType";
        Landscape landscape = LandscapeType.valueOf(name).createInstance();

        assertNotNull(landscape);
        assertTrue(landscape.getLandscapeType() == LandscapeType.valueOf(name));
    }


}
