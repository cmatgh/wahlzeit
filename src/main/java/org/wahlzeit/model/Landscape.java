package org.wahlzeit.model;

import static com.google.common.base.Preconditions.checkNotNull;

/*
 *
 * LandscapePhoto/Landscape
 * public collaboration ClientService{
 *   public role Client{
 *      // no methods
 *   }
 *
 *   public role Service{
 *       public getService();
 *   }
 *}
 *
 */

/*
 *
 * Landscape/LandscapeType
 * public collaboration TypeObject{
 *   public role BaseObject{
 *      public void setTypeObject(TypeObject to);
 *   }
 *
 *   public role TypeObject{
 *       public BaseObject createInstance();
 *   }
 *}
 *
 */
public class Landscape /* binds ClientService.Service */ /* binds TypeObject.BaseObject */{

    private Location location;

    private LandscapeType landscapeType;

    public LandscapeType getLandscapeType() {
        return landscapeType;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    /*
    * TypeObject.BaseObject::setTypeObject(TypeObject to)
     */
    public Landscape(LandscapeType landscapeType){
        checkNotNull(landscapeType);

        this.landscapeType = landscapeType;
    }
}