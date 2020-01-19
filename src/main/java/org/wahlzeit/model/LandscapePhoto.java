package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class LandscapePhoto extends Photo{



    private String photographer;
    private String camera;
    private int exposure;
    private Landscape landscape;


    public String getPhotographer() {
        return photographer;
    }

    public String getCamera() {
        return camera;
    }

    public int getExposure() {
        return exposure;
    }

    public Landscape getLandscape() {
        return landscape;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setExposure(int exposure) {
        this.exposure = exposure;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    /**
     *
     */
    public LandscapePhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public LandscapePhoto(PhotoId myId) {
        super(myId);
    }
}
