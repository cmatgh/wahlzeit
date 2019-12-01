package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;

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

    private void setY(double y){
        this.y = y;
    }

    private void setZ(double z){
        this.z = z;
    }

    public CartesianCoordinate(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);

        assert this.x == x;
        assert this.y == y;
        assert this.z == z;
    }

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    protected double doGetCartesianDistance(Coordinate coordinate){
        double x = getX();
        double y = getY();
        double z = getZ();

        double distance = basicGetCartesianDistance(coordinate.asCartesianCoordinate());

        assert x == getX();
        assert y == getY();
        assert z == getZ();

        return distance;
    }

    private double basicGetCartesianDistance(CartesianCoordinate coordinate){
        double dx = this.getX() - coordinate.getX();
        double dy = this.getY() - coordinate.getY();
        double dz = this.getZ() - coordinate.getZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public SphericCoordinate doAsSphericCoordinate() {
        double x = getX();
        double y = getY();
        double z = getZ();

       SphericCoordinate sphericCoordinate = basicToSphericCoordinate();

        assert x == getX();
        assert y == getY();
        assert z == getZ();

        return sphericCoordinate;
    }

    private SphericCoordinate basicToSphericCoordinate(){
        double r = Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
        if(r == 0){
            return new SphericCoordinate(0d, 0d, 0d);
        }
        double theta = Math.atan2(getY(),getX());
        double phi = Math.acos(getZ()/r);

        return new SphericCoordinate(r,theta, phi);
    }

    @Override
    protected double doGetCentralAngle(Coordinate coordinate){
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    public boolean doIsEqual(Coordinate coordinate){
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

    @Override
    protected void assertClassInvariants(){ }
}