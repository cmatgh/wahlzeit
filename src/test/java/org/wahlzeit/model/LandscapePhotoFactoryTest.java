package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LandscapePhotoFactoryTest {

    private final LocalServiceTestHelper helper
            = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    @Before
    public void setUp() {
        helper.setUp();
        closeable = ObjectifyService.begin();
        LandscapePhotoFactory.instance = null;
    }

    @After
    public void tearDown() {
        closeable.close();
        helper.tearDown();
        LandscapePhotoFactory.instance = null;
    }

    @Test
    public void testGetInstanceReturnsInstanceOfLandscapePhotoFactory(){
        PhotoFactory photoFactory = LandscapePhotoFactory.getInstance();

        assertTrue(photoFactory instanceof LandscapePhotoFactory);
    }

    @Test
    public void testInitializeCreatesInstanceOfLandscapePhotoFactory(){
        LandscapePhotoFactory.initialize();

        assertTrue(LandscapePhotoFactory.instance instanceof LandscapePhotoFactory);
    }

    @Test
    public void testCreatePhotoCreatesInstanceOfLandscapePhoto(){
        PhotoFactory photoFactory = LandscapePhotoFactory.getInstance();

        assertTrue(photoFactory.createPhoto() instanceof LandscapePhoto);
    }

    @Test
    public void testCreatesPhotoWithIdCreatesInstanceOfLandscapePhoto(){
        PhotoFactory photoFactory = LandscapePhotoFactory.getInstance();

        assertTrue(photoFactory.createPhoto(new PhotoId(0)) instanceof LandscapePhoto);
    }
}
