package org.wahlzeit.main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LandscapesMainTest {

    @Before
    public void setUp(){
        LandscapesMain.instance = null;
    }

    @After
    public void tearDown(){
        LandscapesMain.instance = null;
    }

    @Test
    public void testGetInstanceReturnsInstanceOfLandscapesMain(){
        ServiceMain serviceMain = LandscapesMain.getInstance();

        assertTrue(serviceMain instanceof LandscapesMain);
    }
}
