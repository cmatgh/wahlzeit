package org.wahlzeit.main;

import org.wahlzeit.model.LandscapePhotoFactory;
import org.wahlzeit.model.LandscapePhotoManager;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class LandscapesMain extends ServiceMain{

    private static final Logger log = Logger.getLogger(LandscapesMain.class.getName());

    /**
     *
     */
    public static ServiceMain getInstance() {
        if(instance == null){
            setInstance(new LandscapesMain());
        }
        return instance;
    }

    @Override
    public void startUp(boolean inProduction, String rootDir) throws Exception {

        log.config(LogBuilder.createSystemMessage().addAction("init LandscapePhotoFactory").toString());
        LandscapePhotoFactory.initialize();

        log.config(LogBuilder.createSystemMessage().addAction("load LandscapePhotos").toString());
        LandscapePhotoManager.getInstance().init();

        log.config(LogBuilder.createSystemMessage().addAction("startup ServiceMain").toString());
        super.startUp(inProduction, rootDir);
    }


}
