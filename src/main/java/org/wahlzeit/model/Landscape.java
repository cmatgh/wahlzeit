package org.wahlzeit.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class Landscape{

    private Location location;

    private LandscapeType landscapeType;

    public LandscapeType getLandscapeType() {
        return landscapeType;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Landscape(LandscapeType landscapeType){
        checkNotNull(landscapeType);

        this.landscapeType = landscapeType;
    }
}