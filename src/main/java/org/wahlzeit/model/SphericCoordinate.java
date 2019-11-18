package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate  implements Coordinate{

    private static final double DELTA = 0.0001d;

    private double radius;
    private double theta;
    private double phi;

    public double getRadius() {
        return radius;
    }

    public double getTheta() { return theta; }

    public double getPhi() {
        return phi;
    }

    public SphericCoordinate(double radius, double theta, double phi){
        if(radius < 0){
            throw new IllegalArgumentException();
        }
        this.radius = radius;

        this.theta = theta %  Math.PI;
        if(this.theta < 0){
            this.theta += Math.PI;
        }

        this.phi = phi % (2 * Math.PI);
        if(this.phi < 0){
            this.phi +=  (2 * Math.PI);
        }

    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = getRadius() * Math.sin(getPhi()) * Math.cos(getTheta());
        double y = getRadius() * Math.sin(getPhi()) * Math.sin(getTheta());
        double z = getRadius() * Math.cos(getPhi());
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(null == coordinate){
            throw new IllegalArgumentException();
        }

        SphericCoordinate c2 = coordinate.asSphericCoordinate();

        return doGetCartesianDistance(this, c2);
    }

    private double doGetCartesianDistance(SphericCoordinate c1, SphericCoordinate c2){
        return Math.sqrt(c1.getRadius()* c1.getRadius() + c2.getRadius() * c2.getRadius() - 2d * c1.getRadius() * c2.getRadius() * (Math.sin(c1.phi) * Math.sin(c2.phi) * Math.cos(c1.theta - c2.theta) + Math.cos(c1.phi) * Math.cos(c2.phi)));
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
       if(null == coordinate){
           throw new IllegalArgumentException();
       }

        return doGetCentralAngle(this,coordinate.asSphericCoordinate());
    }

    private double doGetCentralAngle(SphericCoordinate coordinate1, SphericCoordinate coordinate2){
        if(coordinate1.getRadius() <= DELTA || coordinate2.getRadius() <= DELTA){
            return 0;
        }
        double dLong = Math.abs((coordinate1.getTheta() - Math.PI) - (coordinate2.getTheta() - Math.PI));
        double lat1 = coordinate1.getPhi()- Math.PI/2;
        double lat2 = coordinate2.getPhi()- Math.PI/2;

        double t1 = Math.cos(lat2) * Math.sin(dLong);
        double t2 = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLong);
        double denom = (Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dLong));
        if(denom == 0){
            return 0;
        }
        return Math.abs(Math.atan(Math.sqrt(t1*t1 + t2*t2)/denom));
    }

    @Override
    public boolean equals(Object o) {
        if(null == o){
            return false;
        }
        if (this == o){
            return true;
        }
        if (!(o instanceof Coordinate)){
            return false;
        }

        return isEqual((Coordinate) o);
    }

    @Override
    public boolean isEqual(Coordinate coordinate){
        if(null == coordinate){
            return false;
        }
        if(this == coordinate){
            return true;
        }

        SphericCoordinate coord = coordinate.asSphericCoordinate();

        return Math.abs(this.getRadius() - coord.getRadius()) <= DELTA
                && Math.abs(this.getPhi() - coord.getPhi()) <= DELTA
                && Math.abs(this.getTheta() - coord.getTheta()) <= DELTA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadius(), getPhi(), getTheta());
    }

    @Override
    public String toString(){
        return "(" + getRadius() +", " + getPhi() + ", " + getTheta() + ")";
    }

}
