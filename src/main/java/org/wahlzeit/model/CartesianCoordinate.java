package org.wahlzeit.model;

import java.util.Objects;

public class CartesianCoordinate implements Coordinate{

    private static final CartesianCoordinate CENTER = new CartesianCoordinate(0d, 0d, 0d);
    private static final double DELTA = 0.0001d;

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException();
        }
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        return doGetCartesianDistance(this, coord);
    }

    private double doGetCartesianDistance(CartesianCoordinate c1, CartesianCoordinate c2){
        double dx = c1.getX() - c2.getX();
        double dy = c1.getY() - c2.getY();
        double dz = c1.getZ() - c2.getZ();

        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r = Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
        if(r == 0){
            return new SphericCoordinate(0d, 0d, 0d);
        }
        double theta = Math.atan2(getY(),getX());
        double phi = Math.acos(getZ()/r);

        SphericCoordinate sphericCoordinate = new SphericCoordinate(r,theta, phi);
        return sphericCoordinate;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if(coordinate == null){
            throw new IllegalArgumentException();
        }

        return doGetCentralAngle(coordinate.asCartesianCoordinate());
    }

    private double doGetCentralAngle(CartesianCoordinate coordinate){
      CartesianCoordinate normalized1 = this.normalize();
      CartesianCoordinate normalized2 = coordinate.normalize();
      double nom = cross(normalized1, normalized2).norm();
      double denom = dot(normalized1, normalized2);
      if(denom == 0){
          return 0;
      }
      return Math.abs(Math.atan(nom/denom));
    }

    private CartesianCoordinate normalize(){
        double norm = norm();
        if(norm == 0){
            return CENTER;
        }
        return new CartesianCoordinate(getX()/norm, getY()/norm, getZ()/norm);
    }

    private CartesianCoordinate cross(CartesianCoordinate coordinate1, CartesianCoordinate coordinate2){
        return new CartesianCoordinate(coordinate1.getY() * coordinate2.getZ() - coordinate1.getZ() * coordinate2.getY()
        ,coordinate1.getZ() * coordinate2.getX() - coordinate1.getX() * coordinate2.getZ()
        ,coordinate1.getX() * coordinate2.getY() - coordinate1.getY() * coordinate2.getX());
    }

    private double dot(CartesianCoordinate coord1, CartesianCoordinate coord2){
        return coord1.getX() * coord2.getX() + coord1.getY() * coord2.getY() +coord1.getZ() * coord2.getZ();
    }

    private double norm(){
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Coordinate)) return false;

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