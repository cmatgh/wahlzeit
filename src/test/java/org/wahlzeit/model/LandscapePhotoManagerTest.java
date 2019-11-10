package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.*;

import static org.junit.Assert.assertTrue;

public class LandscapePhotoManagerTest {

    private final LocalServiceTestHelper helper
            = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable closeable;

    @Before
    public void setUp() {
        helper.setUp();
        closeable = ObjectifyService.begin();
        LandscapePhotoManager.instance = null;
    }

    @After
    public void tearDown() {
        closeable.close();
        helper.tearDown();
        LandscapePhotoManager.instance = null;
    }

    @Test
    public void testLandscapePhotoManagerGetInstanceReturnsInstanceOfLandscapePhotoManager(){
        PhotoManager photoManager = LandscapePhotoManager.getInstance();

        assertTrue(photoManager instanceof LandscapePhotoManager);
        PhotoManager.instance = null;
    }

}
