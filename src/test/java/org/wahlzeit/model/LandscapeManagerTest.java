package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LandscapeManagerTest {

    @Test
    public void testGetInstance(){
        LandscapeManager landscapeManager = LandscapeManager.getInstance();

        assertNotNull(landscapeManager);
    }

    @Test(expected = LandscapeException.class)
    public void testCreateLandscapeOnNullThrowsException() throws LandscapeException{
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        landscapeManager.createLandscape(null);
    }

    @Test
    public void testCreateLandscape() throws LandscapeException{
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        Landscape landscape = landscapeManager.createLandscape("SomeType");

        assertNotNull(landscape);
    }

    @Test(expected = LandscapeException.class)
    public void testCreateLandscapeTypeOnNullThrowsException() throws LandscapeException{
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        landscapeManager.createLandscapeType(null);
    }

    @Test
    public void testCreateLandscapeType() throws LandscapeException{
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        LandscapeType landscapeType = landscapeManager.createLandscapeType("SomeType");

        assertNotNull(landscapeType);
    }

    @Test(expected = LandscapeException.class)
    public void testCreateSubTypeOnNullTypeArgumentAsStringThrowsException() throws LandscapeException {
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        landscapeManager.createSubType((String) null, "SomeType");
    }

    @Test(expected = LandscapeException.class)
    public void testCreateSubTypeOnNullTypeArgumentThrowsException() throws LandscapeException {
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        landscapeManager.createSubType((LandscapeType) null, "SomeType");
    }

    @Test(expected = LandscapeException.class)
    public void testCreateSubTypeOnNullSubtypeThrowsException() throws LandscapeException {
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        landscapeManager.createSubType("SomeType", null);
    }

    @Test
    public void testCreateSubType() throws LandscapeException {
        LandscapeManager landscapeManager = LandscapeManager.getInstance();
        LandscapeType subType = landscapeManager.createSubType("SomeType", "SubType");

        assertNotNull(subType);
        assertTrue(LandscapeType.valueOf("SomeType") == subType.getSuperType());
    }
}
