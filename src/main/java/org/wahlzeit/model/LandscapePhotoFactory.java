package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class LandscapePhotoFactory extends PhotoFactory{

    private static final Logger log = Logger.getLogger(LandscapePhotoFactory.class.getName());

    /**
     *
     */
    protected LandscapePhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }


    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting LandscapePhotoFactory").toString());
            setInstance(new LandscapePhotoFactory());
        }

        return instance;
    }

    /**
     * @methodtype factory
     */
    @Override
    public Photo createPhoto() {
        return new LandscapePhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    @Override
    public Photo createPhoto(PhotoId id) {
        return new LandscapePhoto(id);
    }

}
