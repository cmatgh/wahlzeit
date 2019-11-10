package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class LandscapePhotoManager extends PhotoManager{

    private static final Logger log = Logger.getLogger(LandscapePhotoManager.class.getName());

    /**
     *
     */
    public static synchronized PhotoManager getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting LandscapePhotoManager").toString());
            setInstance(new LandscapePhotoManager());
        }

        return instance;
    }
}
