package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LandscapePhotoTest {

    private final LocalServiceTestHelper helper
            = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    @Before
    public void setUp() {
        helper.setUp();
        closeable = ObjectifyService.begin();
    }

    @After
    public void tearDown() {
        closeable.close();
        helper.tearDown();
    }

    @Test
    public void testGetAndSetCamera(){
        String cameraName = "Nikon";
        LandscapePhoto photo = new LandscapePhoto();
        photo.setCamera(cameraName);

        assertTrue(photo.getCamera().equals(cameraName));
    }

    @Test
    public void testGetAndSetPhotographer(){
        String photographerName = "Adams";
        LandscapePhoto photo = new LandscapePhoto();
        photo.setPhotographer(photographerName);

        assertTrue(photo.getPhotographer().equals(photographerName));
    }

    @Test
    public void testGetAndSetExposure(){
        int exposureTime = 1000;
        LandscapePhoto photo = new LandscapePhoto();
        photo.setExposure(exposureTime);

        assertEquals(photo.getExposure(), exposureTime);
    }
}
