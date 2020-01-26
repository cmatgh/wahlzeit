package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;

@PatternInstance(
        patternName = "Singleton",
        participants = { "Singleton" }
)
public class LandscapeManager {

    private static LandscapeManager instance;

    protected static final Logger log = Logger.getLogger(LandscapePhotoManager.class.getName());

    private LandscapeManager(){}

    protected static LandscapeManager getInstance(){
        if(instance == null){
            synchronized (LandscapeManager.class){
                if(instance == null){
                    instance = new LandscapeManager();
                }
            }
        }
        return instance;
    }

    public Landscape createLandscape(String typeName) throws LandscapeException{
        try{
            return doCreateLandscape(typeName);
        }catch (IllegalArgumentException | NullPointerException  ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new LandscapeException(ex.getMessage());
        }
    }

    private Landscape doCreateLandscape(String typeName){
        LandscapeType landscapeType = LandscapeType.valueOf(typeName);
        Landscape landscape = landscapeType.createInstance();

        return landscape;
    }

    public LandscapeType createLandscapeType(String typeName) throws LandscapeException{
        try{
            return LandscapeType.valueOf(typeName);
        }catch (IllegalArgumentException | NullPointerException ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new LandscapeException(ex.getMessage());
        }
    }

    public LandscapeType createSubType(String parent, String subtype) throws LandscapeException{
        try{
            return createSubType(LandscapeType.valueOf(parent), subtype);
        }catch (IllegalArgumentException | NullPointerException ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new LandscapeException(ex.getMessage());
        }
    }

    public LandscapeType createSubType(LandscapeType parent, String subtype) throws LandscapeException{
        try{
            checkNotASubtypeOf(parent, subtype);

            return doCreateSubType(parent, subtype);
        }catch (IllegalArgumentException | NullPointerException ex){
            log.warning(LogBuilder.createSystemMessage().
                    addException(ex.getMessage(), ex).toString());
            throw new LandscapeException(ex.getMessage());
        }
    }

    private LandscapeType doCreateSubType(LandscapeType parent, String subType) {
        parent.addSubType(LandscapeType.valueOf(subType));
        return LandscapeType.valueOf(subType);
    }

    private void checkNotASubtypeOf(LandscapeType parent, String subType) throws LandscapeException{
        if(parent.hasSubType(subType)){
            throw new LandscapeException("subtype already present in subtree, please remove subtype first");
        }
    }
}