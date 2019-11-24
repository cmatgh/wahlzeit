package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;

    private double norm = Double.NaN;

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    private void setX(double x){ this.x = x; }

    private void setY(double y){ this.y = y; }

    private void setZ(double z){ this.z = z; }


    public CartesianCoordinate(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    protected double doGetCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();

        return calculateCartesianDistance(cartesianCoordinate);
    }

    private double calculateCartesianDistance(CartesianCoordinate coordinate){
        double dx = this.getX() - coordinate.getX();
        double dy = this.getY() - coordinate.getY();
        double dz = this.getZ() - coordinate.getZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r = norm();
        if(r == 0){
            return new SphericCoordinate(0d, 0d, 0d);
        }
        double theta = Math.atan2(getY(),getX());
        double phi = Math.acos(getZ()/r);

        SphericCoordinate sphericCoordinate = new SphericCoordinate(r,theta, phi);
        return sphericCoordinate;
    }

    private double norm(){
        if(Double.isNaN(norm)){
            norm = Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
        }
        return norm;
    }

    @Override
    protected double doGetCentralAngle(Coordinate coordinate){
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    public boolean isEqual(Coordinate coordinate){
        if(null == coordinate){
            return false;
        }
        if(this == coordinate){
            return true;
        }

        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        return Math.abs(this.getX() - coord.getX()) <= DELTA
                && Math.abs(this.getY() - coord.getY()) <= DELTA
                && Math.abs(this.getZ() - coord.getZ()) <= DELTA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    @Override
    public String toString(){
        return "(" + getX() +", " + getY() + ", " + getZ() + ")";
    }
}