package org.wahlzeit.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate{

    private final static Map<Integer, CartesianCoordinate> cache = new HashMap<>();

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

    public static CartesianCoordinate valueOf(double x, double y, double z) {
        CartesianCoordinate coordinate = putToCacheIfAbsentAndReturn(x,y,z);

        assert coordinate != null;

        return coordinate;
    }

    private static CartesianCoordinate putToCacheIfAbsentAndReturn(double x, double y, double z){
        int hash = Objects.hash(x, y, z);
        if(!cache.containsKey(hash)){
            synchronized (CartesianCoordinate.class){
                if(!cache.containsKey(hash)) {
                    CartesianCoordinate coordinate = new CartesianCoordinate(x, y, z);
                    cache.put(coordinate.hashCode(), coordinate);
                }
            }
        }

        return cache.get(hash);
    }

    private CartesianCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;

        assert this.x == x;
        assert this.y == y;
        assert this.z == z;
    }

    @Override
    protected CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    protected double doGetCartesianDistance(Coordinate coordinate) throws CoordinateException {
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
    protected SphericCoordinate doAsSphericCoordinate() throws CoordinateException {

            double x = getX();
            double y = getY();
            double z = getZ();

            SphericCoordinate sphericCoordinate = basicToSphericCoordinate();

            assert x == getX();
            assert y == getY();
            assert z == getZ();

            return sphericCoordinate;
    }

    private SphericCoordinate basicToSphericCoordinate() throws CoordinateException {
        double r = Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
        if(r == 0){
            return SphericCoordinate.valueOf(0d, 0d, 0d);
        }
        double theta = Math.atan2(getY(),getX());
        double phi = Math.acos(getZ()/r);

        return SphericCoordinate.valueOf(r,theta, phi);
    }

    @Override
    protected double doGetCentralAngle(Coordinate coordinate) throws CoordinateException {
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    protected boolean doIsEqual(Coordinate coordinate) throws CoordinateException {
        CartesianCoordinate coord = coordinate.asCartesianCoordinate();

        return coord == this;
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