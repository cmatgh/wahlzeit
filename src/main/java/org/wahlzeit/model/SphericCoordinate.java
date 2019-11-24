package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

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

    private void setTheta(double theta){
        this.theta = theta %  Math.PI;
        if(this.theta < 0){
            this.theta += Math.PI;
        }
    }

    private void setPhi(double phi){
        this.phi = phi % (2 * Math.PI);
        if(this.phi < 0){
            this.phi +=  (2 * Math.PI);
        }
    }

    private void setRadius(double radius){
        if(radius < 0){
            throw new IllegalArgumentException();
        }
        this.radius = radius;
    }

    public SphericCoordinate(double radius, double theta, double phi){
        setRadius(radius);
        setTheta(theta);
        setPhi(phi);
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = getRadius() * Math.sin(getPhi()) * Math.cos(getTheta());
        double y = getRadius() * Math.sin(getPhi()) * Math.sin(getTheta());
        double z = getRadius() * Math.cos(getPhi());
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    protected double doGetCartesianDistance(Coordinate coordinate){
        return asCartesianCoordinate().doGetCartesianDistance(coordinate);
   }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    protected double doGetCentralAngle(Coordinate coordinate){
        SphericCoordinate c1 = this.asSphericCoordinate();
        SphericCoordinate c2 = coordinate.asSphericCoordinate();

        if(c1.getRadius() <= DELTA || c2.getRadius() <= DELTA){
            return 0;
        }
        double dLong = Math.abs((c1.getTheta() - Math.PI) - (c2.getTheta() - Math.PI));
        double lat1 = c1.getPhi()- Math.PI/2;
        double lat2 = c2.getPhi()- Math.PI/2;

        double t1 = Math.cos(lat2) * Math.sin(dLong);
        double t2 = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLong);
        double denom = (Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dLong));
        if(denom == 0){
            return 0;
        }
        return Math.abs(Math.atan(Math.sqrt(t1*t1 + t2*t2)/denom));
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
        return Objects.hash(getRadius(), getTheta(), getPhi());
    }

    @Override
    public String toString(){
        return "(" + getRadius() +", " + getTheta() + ", " + getPhi() + ")";
    }

}
